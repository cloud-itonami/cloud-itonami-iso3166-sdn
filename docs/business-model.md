# Business model — SDN

Independent public-sector market-entry & procurement compliance service for
Sudan: helps a market-entry operator assemble and track the evidence a
Procurement, Contracting & Disposal of Surplus Act 2010 filing needs, draft
a filing package, and (with human sign-off) submit it -- never itself an
official government registry or portal.

## What this actor does

1. **Engagement intake** -- records the operator engagement (client,
   jurisdiction, fee terms).
2. **Jurisdiction assessment** -- looks up Sudan's own required-evidence
   checklist (`marketentry.facts`) and proposes it, citing the specific
   official source.
3. **Filing draft** -- prepares an unsigned filing-draft record (an
   append-only book-of-record entry, not a real portal submission).
4. **Filing submit** -- prepares the filing-submit record. This is the
   real-world act of actually submitting a registration/filing; it is
   ALWAYS gated on human approval (see Trust Controls below).

## Grounding: what is and is not verified for Sudan

This actor's dossier is intentionally **thinner and more conservative**
than sibling jurisdictions in this actor family, including the already-thin
`cloud-itonami-iso3166-stp` (São Tomé and Príncipe): Sudan's public web
presence for government/regulatory information is limited, and the
country's current instability further limits verifiable current
information. What is verified:

- **The Procurement, Contracting & Disposal of Surplus Act**, promulgated
  in 2010, with implementing regulations passed in 2011, is the current
  legal basis for public procurement. It established a General Directorate
  for Procurement, Contracting & Disposal of Surplus, tasked with
  regulating and supervising public procurement in Sudan. The primary
  legal text is indexed (hosted in Arabic) at the World Bank's PPP
  Knowledge Lab library:
  `https://ppp.worldbank.org/library/public-procurement-contracting-and-disposal-public-assets-act-2010-arabic`.
- Before this 2010 Act, public procurement was regulated under the
  Accounting & Financial Regulations issued in 1978 (amended 1995). This
  prior regime is recorded ONLY as historical context in
  `marketentry.facts` -- it is explicitly NOT cited as the current legal
  basis.
- **Commercial registration** is maintained by the **Ministry of Justice
  of Sudan**, which is separately responsible for the commercial register.
- **Corporate/company registration** is regulated by the **Ministry of
  Finance and Economic Planning**, the main regulatory authority for
  company registration in Sudan.
- **Corporate law basis**: the Companies Act of 1925, the Law on
  Registration of 1971, and the Law on Company Names of 1931 (plus other
  unspecified statutes) govern corporate practice in Sudan.
- **Registration friction (real, not softened)**: online company
  registration is **not available** in Sudan -- a foreign entrepreneur
  must travel to the country and complete registration in person. This is
  a genuine operational cost of market entry, not a governor-checkable
  evidence item, so it is recorded here and in `docs/operator-guide.md`
  rather than encoded into `marketentry.facts`'s required-evidence list.

What is NOT verified, and is therefore NOT claimed:

- A national transactional e-procurement portal for Sudan. (Contrast
  Angola's SNCP e-procurement system.) This actor's own `:national-spec`
  field says so explicitly.
- A specific supplier-registration document checklist analogous to
  Angola's NIF/Registo Comercial or Fiji's FRCA/FNPF, beyond the two
  registration facts above.
- A tax-ID/corporate-number verification scheme distinct from the
  Ministry of Justice commercial register and Ministry of Finance and
  Economic Planning company registration already recorded.

**This is Sudan (SDN), not South Sudan (SSD).** These are two different
countries with different ISO3166 codes, different governments, and
entirely separate legal/procurement systems, split since South Sudan's
2011 independence. Research for this catalog was deliberately filtered to
exclude South Sudan material, which shares a name prefix and frequently
pollutes naive "Sudan procurement" web searches.

## Trust Controls

- **Every jurisdiction requirement this actor states traces to an
  official source cited in `marketentry.facts`.** A proposal that cannot
  cite one is a HARD governor violation (`:no-spec-basis`) -- a false or
  fabricated regulatory-requirement claim is a HARD hold, unconditionally.
- **Any actual filing draft or filing submission requires Market-Entry
  Compliance Governor clearance and always escalates to human sign-off**
  -- `:filing/draft`/`:filing/submit` are permanently absent from every
  rollout phase's auto-commit set (`marketentry.phase`), and the
  governor's own high-stakes gate (`marketentry.governor`) independently
  enforces the same rule. Two layers, not one, agree that actuation is
  always a human call.
- **Missing Sudan commercial/corporate registration is an unoverridable
  HARD hold** when the engagement declares it is required
  (`registration-missing`, the SDN analog of the AGO reference
  implementation's flagship `ao-entity-missing` check and STP's
  `commercial-registration-missing` check).
- **A claimed engagement fee that does not equal the independently
  recomputed `base-fee + monthly-rate x monitoring-months` is an
  unoverridable HARD hold** (`engagement-fee-mismatch`).
- **This is a SIX-check governor, not the AGO reference's seven.** The
  Sudan dossier does not ground a distinct tax-ID/corporate-number
  verification scheme separate from the Ministry of Justice / Ministry of
  Finance and Economic Planning registration facts, so there is no analog
  of AGO's `nif-unverified` check here. Padding the check count to match a
  sibling actor would itself be a fabrication -- honesty about a thinner
  dossier is a feature of this actor's design, not a gap to paper over.
- **Double-actuation is structurally prevented**: `:drafted?`/
  `:submitted?` dedicated facts (never a `:status` value) make drafting
  or submitting the same engagement twice an unoverridable HARD hold.
- **Every governor decision -- commit OR hold -- is written to an
  append-only audit ledger.** Nothing is silently dropped.

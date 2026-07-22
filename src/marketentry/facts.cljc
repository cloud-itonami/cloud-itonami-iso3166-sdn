(ns marketentry.facts
  "Sudan (SDN) market-entry catalog.

  CRITICAL: this is Sudan (SDN), NOT South Sudan (SSD) -- two different
  countries with different ISO3166 codes, different governments, and
  entirely separate legal/procurement systems, split since South Sudan's
  2011 independence. Every fact in this catalog was filtered during
  research (2026-07-22) to be Sudan-specific; South Sudan material (which
  shares a name prefix and pollutes many naive web searches for 'Sudan
  procurement') was deliberately excluded. No fact below traces to a
  South-Sudan source.

  This catalog is DELIBERATELY thinner and more conservative than sibling
  jurisdictions in this actor family (compare `cloud-itonami-iso3166-ago`'s
  `AGO` entry, or even `cloud-itonami-iso3166-stp`'s already-thin `STP`
  entry): Sudan's public web presence for government/regulatory
  information is limited, and the country's current instability further
  limits verifiable current information. What follows is exactly what
  research established, no more:

  - **Procurement legal basis**: the Procurement, Contracting & Disposal
    of Surplus Act, promulgated in 2010, with implementing regulations
    passed in 2011. This Act established a General Directorate for
    Procurement, Contracting & Disposal of Surplus, tasked with
    regulating and supervising public procurement in Sudan. The primary
    legal text is indexed (hosted in Arabic) at the World Bank's PPP
    Knowledge Lab library as 'Public Procurement, Contracting and
    Disposal of Public Assets Act 2010' --
    https://ppp.worldbank.org/library/public-procurement-contracting-and-disposal-public-assets-act-2010-arabic
    -- reached via secondary commentary summarizing the Act; the
    provenance URL points at that library entry, not a directly-fetched
    English full text.
  - **Prior regime (superseded, NOT current law)**: before February 2010,
    public procurement was regulated under the Accounting & Financial
    Regulations issued in 1978, with amendments in 1995. This is cited
    here ONLY as historical context for why the 2010 Act matters -- it is
    NOT the current legal basis and MUST NOT be used as `:legal-basis`.
  - **Business/commercial registration authority**: the Ministry of
    Finance and Economic Planning is the main regulatory authority for
    company registration in Sudan; the Ministry of Justice of Sudan is
    SEPARATELY responsible for maintaining the commercial register. Two
    distinct ministries, two distinct registration facts -- this catalog
    keeps them as two separate `:required-evidence` line items rather
    than merging them into one.
  - **Corporate law basis**: the Companies Act of 1925, the Law on
    Registration of 1971, and the Law on Company Names of 1931 (plus
    other unspecified statutes) govern corporate practice in Sudan.
  - **Registration process friction (real, not softened)**: online
    company registration is NOT available in Sudan -- foreign
    entrepreneurs must travel to the country and complete registration in
    person. This is a genuine operational friction fact worth recording
    honestly; it does not change any governor check (it is not itself a
    verifiable/falsifiable evidence item an engagement record can
    satisfy), so it is documented in `docs/operator-guide.md` and
    `docs/business-model.md` rather than encoded as a required-evidence
    line here.

  What is NOT claimed, because it was NOT found:

  - No national e-procurement portal was found/verified for Sudan --
    `:national-spec` says so honestly rather than inventing one (contrast
    Angola's SNCP e-procurement system).
  - No specific supplier-registration document checklist (analogous to
    Angola's NIF/Registo Comercial, or Fiji's FRCA/FNPF) was found for
    Sudan's procurement system specifically -- `:required-evidence` below
    is therefore SHORT (two items): commercial registration via the
    Ministry of Justice's register, and corporate registration via the
    Ministry of Finance and Economic Planning. A short list here is the
    honest, correct outcome, not a gap to be padded.
  - No distinct resident-representative regime and no distinct tax-ID/
    corporate-number verification scheme (separate from the two
    registration facts above) was found -- so, like `STP`, this entry
    carries no `:rep-*`/`:corporate-number-*` sub-map.")

(def catalog
  {"SDN" {:name "Sudan"
          :owner-authority "General Directorate for Procurement, Contracting & Disposal of Surplus (established under the Procurement, Contracting & Disposal of Surplus Act, 2010)"
          :legal-basis "Procurement, Contracting & Disposal of Surplus Act (promulgated 2010; implementing regulations 2011) -- supersedes the 1978 Accounting & Financial Regulations (amended 1995), which are historical context only, not current law"
          :national-spec "Procurement, Contracting & Disposal of Surplus Act 2010 procedures (no verified national e-procurement portal)"
          :provenance "https://ppp.worldbank.org/library/public-procurement-contracting-and-disposal-public-assets-act-2010-arabic"
          :required-evidence ["Commercial registration record (Ministry of Justice of Sudan commercial register)"
                               "Corporate registration record (Ministry of Finance and Economic Planning)"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))

# Operator guide — SDN

Human-gated filing only.

## What runs automatically

- `:engagement/intake` may auto-commit at phase 3 when the governor is
  clean (no portal-facing risk).

## What always needs your sign-off

- `:jurisdiction/assess` -- always requires human approval, even when
  clean.
- `:filing/draft` -- always requires human approval. This prepares an
  unsigned filing-draft record; it does not itself submit anything to a
  government registry.
- `:filing/submit` -- always requires human approval. This is the
  real-world act of submitting a filing; there is no phase, no
  confidence score, and no governor state that lets this auto-commit.

## What will always be refused, with no override

- A jurisdiction proposal that cannot cite an official source
  (`marketentry.facts`) -- `:no-spec-basis`.
- A `:filing/draft`/`:filing/submit` proposal before the jurisdiction has
  a full evidence checklist on file -- `:evidence-incomplete`.
- A `:filing/submit` for an engagement that declares it requires Sudan
  commercial/corporate registration (Ministry of Justice commercial
  register + Ministry of Finance and Economic Planning company
  registration) but has not confirmed it -- `:registration-missing`.
- A `:filing/submit` whose claimed fee does not equal
  `base-fee + monthly-rate x monitoring-months` -- `:engagement-fee-mismatch`.
- Drafting or submitting the same engagement a second time --
  `:already-drafted` / `:already-submitted`.

## What this actor does NOT claim

There is no national e-procurement portal on file for Sudan -- the
operative legal basis is the **Procurement, Contracting & Disposal of
Surplus Act** (2010, implementing regulations 2011), administered by the
General Directorate for Procurement, Contracting & Disposal of Surplus. If
you find a verifiable source for a national e-procurement portal or a more
detailed evidence checklist, extend `src/marketentry/facts.cljc`'s
`catalog` -- do not hand-edit a claim into this guide or any other doc
without an official source.

## Operational friction this actor does NOT paper over

Sudan does not offer online company registration. If your engagement
needs a new Sudanese commercial/corporate registration record and does
not already have one, plan for an **in-person** registration trip -- a
foreign entrepreneur must travel to Sudan to complete registration; there
is no remote/online path. This actor tracks whether registration is on
file (`:requires-sdn-registration?`/`:has-sdn-registration?`); it cannot
perform the registration trip for you.

## This is Sudan (SDN), not South Sudan (SSD)

If you are looking for South Sudan (independent since 2011, ISO3166 code
SSD), this is the wrong actor -- Sudan and South Sudan have entirely
separate governments, legal systems and procurement regimes. Nothing in
this repo's catalog was sourced from South Sudan material.

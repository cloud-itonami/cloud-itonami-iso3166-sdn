# ADR-0001: SDN

Adapted from the `cloud-itonami-iso3166-ago` reference implementation,
following the six-check pattern `cloud-itonami-iso3166-stp` established for
thinner-dossier jurisdictions. Flagship check `registration-missing` (Sudan
commercial registration via the Ministry of Justice + corporate/company
registration via the Ministry of Finance and Economic Planning). **Six**
governor checks, not AGO's seven -- the Sudan dossier does not ground a
distinct tax-ID/corporate-number scheme separate from those two
registration facts, so there is no analog of AGO's `nif-unverified` check.

Sudan's public web presence for government/regulatory information is
limited, and the country's current instability further limits verifiable
current information -- this dossier is deliberately thinner even than
`cloud-itonami-iso3166-stp`'s (2 required-evidence items vs STP's 3, AGO's
4). See `docs/business-model.md` Trust Controls for the full research
basis.

**This is Sudan (SDN), not South Sudan (SSD).** Research for this catalog
was deliberately filtered to exclude South Sudan material, which shares a
name prefix and frequently pollutes naive "Sudan procurement" web
searches. No South Sudan fact appears anywhere in this repo's
`marketentry.facts` catalog.

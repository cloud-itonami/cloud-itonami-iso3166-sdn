# cloud-itonami-iso3166-sdn

**`:implemented`** for **SDN** (Sudan): an "Actors" pattern market-entry /
public-procurement compliance service (Governor + LLM advisor +
langgraph-clj StateGraph + append-only audit ledger + Store), adapted from
the `cloud-itonami-iso3166-ago` reference implementation, following the
same six-check pattern `cloud-itonami-iso3166-stp` established for
thinner-dossier jurisdictions.

Flagship check: `registration-missing` (Sudan commercial registration via
the Ministry of Justice + corporate/company registration via the Ministry
of Finance and Economic Planning). **Six** governor checks, not the AGO
reference's seven -- see `src/marketentry/governor.cljc` for why.

```
clojure -M:dev:test
```

## Grounding: this dossier is intentionally thin

Sudan's public web presence for government/regulatory information is
limited, and the country's current instability further limits verifiable
current information -- this actor's `src/marketentry/facts.cljc` catalog
is deliberately thinner than sibling jurisdictions (including the
already-thin `cloud-itonami-iso3166-stp`) rather than padded to look more
complete than the evidence supports. What IS verified, and is what this
actor's catalog cites:

- **Procurement legal basis**: the Procurement, Contracting & Disposal of
  Surplus Act, promulgated in 2010 with implementing regulations in 2011,
  which established a General Directorate for Procurement, Contracting &
  Disposal of Surplus. Primary text indexed (hosted in Arabic) at the
  World Bank PPP Knowledge Lab library.
- **Commercial registration**: Ministry of Justice of Sudan (commercial
  register).
- **Corporate/company registration**: Ministry of Finance and Economic
  Planning.
- **Corporate law basis**: Companies Act 1925, Law on Registration 1971,
  Law on Company Names 1931.

What is NOT claimed: no national e-procurement portal, no
supplier-registration document checklist beyond the two registration
facts above, no distinct tax-ID/corporate-number scheme. See
`docs/business-model.md` and `docs/operator-guide.md` for the full
research basis, including the genuine in-person-only registration
friction this actor honestly surfaces rather than glosses over.

**This is Sudan (SDN), not South Sudan (SSD)** -- two different countries
with different ISO3166 codes and entirely separate legal/procurement
systems, split since South Sudan's 2011 independence. No South Sudan fact
appears anywhere in this catalog.

AGPL-3.0-or-later.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Sudan:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.

(ns culture.facts
  "Country-level regional-culture catalog for Sudan (SDN) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"SDN"
   [{:culture/id "sdn.dish.kisra"
     :culture/name "Kisra"
     :culture/country "SDN"
     :culture/kind :dish
     :culture/summary "Sudanese thin, fermented flatbread traditionally made from sorghum flour and eaten as the staple accompaniment to Sudanese stews; also made in Chad, South Sudan and parts of Uganda and Kenya."
     :culture/url "https://en.wikipedia.org/wiki/Kisra"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sdn.dish.asida"
     :culture/name "Asida"
     :culture/country "SDN"
     :culture/kind :dish
     :culture/summary "In Sudan, asida is a sorghum porridge popular during Ramadan and often served with a meat-and-tomato sauce; the dish more broadly is common across the Arab world."
     :culture/url "https://en.wikipedia.org/wiki/Asida"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sdn.dish.ful-medames"
     :culture/name "Ful medames"
     :culture/country "SDN"
     :culture/kind :dish
     :culture/summary "Fava-bean stew considered Egypt's national dish and also eaten in Sudan, where it has its own regional variant, among other countries in the region."
     :culture/url "https://en.wikipedia.org/wiki/Ful_medames"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sdn.beverage.karkade"
     :culture/name "Karkade"
     :culture/country "SDN"
     :culture/kind :beverage
     :culture/summary "Hibiscus-sepal infusion served hot or cold; in Egypt and Sudan wedding celebrations are traditionally toasted with a glass of it, and the roselle plant was domesticated in Africa, particularly Western Sudan."
     :culture/url "https://en.wikipedia.org/wiki/Hibiscus_tea"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sdn.product.gum-arabic"
     :culture/name "Gum arabic"
     :culture/country "SDN"
     :culture/kind :product
     :culture/summary "Tree gum harvested from acacia species; since the 1950s the global supply has been dominated by Sudan, which sourced about 70% of the world's gum arabic in the early 2020s, with an estimated 5 million Sudanese depending on it for their livelihoods."
     :culture/url "https://en.wikipedia.org/wiki/Gum_arabic"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sdn.craft.toub"
     :culture/name "Toub"
     :culture/country "SDN"
     :culture/kind :craft
     :culture/summary "Long, rectangular wrapped garment (2m wide, 4-7m long) and the most common women's clothing in Sudan, tracing to depictions of draped garments in Kingdom of Kush and Meroe murals; hand-embroidery and traditional draping remain artisan crafts preserved through apprenticeship."
     :culture/url "https://en.wikipedia.org/wiki/Clothing_in_Sudan"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sdn.festival.nuba-wrestling"
     :culture/name "Nuba wrestling"
     :culture/country "SDN"
     :culture/kind :festival
     :culture/summary "Traditional wrestling of the Nuba peoples of the Nuba Mountains, South Kordofan; in rural areas tournaments are associated with planting and harvest festivals and build group identity, accompanied by feasts, music, dance and storytelling."
     :culture/url "https://en.wikipedia.org/wiki/Nuba_wrestling"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sdn.heritage.meroe"
     :culture/name "Archaeological Sites of the Island of Meroe"
     :culture/name-local "Meroë"
     :culture/country "SDN"
     :culture/kind :heritage
     :culture/summary "Ancient city on the east bank of the Nile about 200km northeast of Khartoum, capital of the Kingdom of Kush from around 590 BC to the 4th century AD; inscribed as a UNESCO World Heritage Site in June 2011."
     :culture/url "https://en.wikipedia.org/wiki/Mero%C3%AB"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-sdn culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "SDN"))
                 " SDN entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))

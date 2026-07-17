(ns culture.facts
  "Country-level regional-culture catalog for Australia (AUS) -- national
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
  {"AUS"
   [{:culture/id "aus.dish.meat-pie"
     :culture/name "Meat pie"
     :culture/country "AUS"
     :culture/kind :dish
     :culture/summary "Handheld savoury pie of minced meat and gravy, iconic in Australia and New Zealand; described in 2003 by the NSW Premier as Australia's national dish."
     :culture/url "https://en.wikipedia.org/wiki/Meat_pie_(Australia_and_New_Zealand)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aus.dish.pavlova"
     :culture/name "Pavlova"
     :culture/country "AUS"
     :culture/kind :dish
     :culture/summary "Meringue-based dessert topped with cream and fruit, named after ballerina Anna Pavlova; its origin is a long-standing dispute between Australia and New Zealand, both of which claim it as a national symbol."
     :culture/url "https://en.wikipedia.org/wiki/Pavlova_(dessert)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aus.dish.lamington"
     :culture/name "Lamington"
     :culture/country "AUS"
     :culture/kind :dish
     :culture/summary "Australian cake of sponge coated in chocolate sauce and desiccated coconut, originating from Queensland and believed to be named after Lord Lamington."
     :culture/url "https://en.wikipedia.org/wiki/Lamington"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aus.dish.anzac-biscuit"
     :culture/name "Anzac biscuit"
     :culture/country "AUS"
     :culture/kind :dish
     :culture/summary "Sweet oat biscuit popular in Australia and New Zealand, long associated with the Australian and New Zealand Army Corps established in World War I."
     :culture/url "https://en.wikipedia.org/wiki/Anzac_biscuit"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aus.product.vegemite"
     :culture/name "Vegemite"
     :culture/country "AUS"
     :culture/kind :product
     :culture/summary "Thick dark-brown Australian food spread made from brewers' yeast extract, developed in Melbourne in 1922 and considered a symbol of Australia."
     :culture/url "https://en.wikipedia.org/wiki/Vegemite"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aus.beverage.flat-white"
     :culture/name "Flat white"
     :culture/country "AUS"
     :culture/kind :beverage
     :culture/summary "Espresso-and-steamed-milk coffee drink whose invention is claimed by café owners in both Australia and New Zealand."
     :culture/url "https://en.wikipedia.org/wiki/Flat_white"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aus.craft.didgeridoo"
     :culture/name "Didgeridoo"
     :culture/country "AUS"
     :culture/kind :craft
     :culture/summary "Wind instrument developed by Aboriginal peoples of northern Australia at least 1,000 years ago, most strongly associated with Indigenous Australian music."
     :culture/url "https://en.wikipedia.org/wiki/Didgeridoo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aus.festival.vivid-sydney"
     :culture/name "Vivid Sydney"
     :culture/country "AUS"
     :culture/kind :festival
     :culture/summary "Annual festival of outdoor light installations, music and ideas held over three weeks in May and June in Sydney, New South Wales."
     :culture/url "https://en.wikipedia.org/wiki/Vivid_Sydney"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aus.heritage.sydney-opera-house"
     :culture/name "Sydney Opera House"
     :culture/country "AUS"
     :culture/kind :heritage
     :culture/summary "Multi-venue performing arts centre in Sydney designed by Jørn Utzon and completed in 1973, designated a UNESCO World Heritage Site in 2007."
     :culture/url "https://en.wikipedia.org/wiki/Sydney_Opera_House"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aus.heritage.uluru"
     :culture/name "Uluru"
     :culture/name-local "Uluṟu"
     :culture/country "AUS"
     :culture/kind :heritage
     :culture/summary "Large sandstone monolith in Australia's Northern Territory, sacred to the Pitjantjatjara people, UNESCO World Heritage listed as part of Uluṟu-Kata Tjuṯa National Park since 1987."
     :culture/url "https://en.wikipedia.org/wiki/Uluru"
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
      :note (str "cloud-itonami-iso3166-aus culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "AUS"))
                 " AUS entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))

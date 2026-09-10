(ns statute.facts
  "General-law compliance catalog for Australia (AUS) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-gbr/-deu/-fra/-can's `statute.facts`
  (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL legislation.gov.au (Federal Register of
  Legislation) URL -- never fabricated. A law not in this table has NO
  spec-basis, full stop; extend `catalog`, do not invent an id/url.
  Title and Act number for every entry below were directly WebFetch-
  verified against the live legislation.gov.au page on 2026-07-15
  (rendered cleanly, like the UK/DE/CA sources).")

(def catalog
  "iso3 -> vector of statute entries."
  {"AUS"
   [{:statute/id "aus.corporations-act-2001"
     :statute/title "Corporations Act 2001"
     :statute/jurisdiction "AUS"
     :statute/kind :law
     :statute/law-number "No. 50, 2001"
     :statute/url "https://www.legislation.gov.au/C2004A00818/latest"
     :statute/url-provenance :official-legislation-gov-au
     :statute/enacted-date "2001"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "aus.privacy-act-1988"
     :statute/title "Privacy Act 1988"
     :statute/jurisdiction "AUS"
     :statute/kind :law
     :statute/law-number "No. 119, 1988"
     :statute/url "https://www.legislation.gov.au/C2004A03712/latest"
     :statute/url-provenance :official-legislation-gov-au
     :statute/enacted-date "1988"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "aus.fair-work-act-2009"
     :statute/title "Fair Work Act 2009"
     :statute/jurisdiction "AUS"
     :statute/kind :law
     :statute/law-number "No. 28, 2009"
     :statute/url "https://www.legislation.gov.au/C2009A00028/latest"
     :statute/url-provenance :official-legislation-gov-au
     :statute/enacted-date "2009"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

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
      :note (str "cloud-itonami-iso3166-aus statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "AUS")) " AUS statutes seeded with an "
                 "official legislation.gov.au citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))

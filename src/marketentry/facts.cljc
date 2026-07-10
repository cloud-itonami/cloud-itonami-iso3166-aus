(ns marketentry.facts
  "Australia market-entry regulatory catalog (G2-style).")
(def catalog
  {"AUS" {:name "Australia"
          :owner-authority "Department of Finance / AusTender"
          :legal-basis "Commonwealth Procurement Rules (CPRs)"
          :national-spec "AusTender registration + ABN"
          :provenance "https://www.tenders.gov.au/"
          :required-evidence ["ABN record"
                              "AusTender registration record"
                              "GST registration record"
                              "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / AusTender"
          :rep-legal-basis "Australian Business Number entity or authorized local presence for many Commonwealth procurements"
          :rep-provenance "https://www.tenders.gov.au/"
          :corporate-number-owner-authority "Australian Taxation Office / ABR"
          :corporate-number-legal-basis "Australian Business Number (ABN)"
          :corporate-number-provenance "https://abr.business.gov.au/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "GBR" {:name "United Kingdom" :owner-authority "Find a Tender" :legal-basis "PCR 2015"
          :national-spec "Find a Tender" :provenance "https://www.find-tender.service.gov.uk/"
          :required-evidence ["Companies House record" "Find a Tender registration record" "VAT registration record" "Authorized-representative record"]}
   "CAN" {:name "Canada" :owner-authority "CanadaBuys" :legal-basis "GCR"
          :national-spec "CanadaBuys" :provenance "https://canadabuys.canada.ca/"
          :required-evidence ["Business Number (BN) record" "CanadaBuys supplier registration" "GST/HST registration record" "Authorized-representative record"]}})
(defn spec-basis [iso3] (get catalog iso3))
(defn coverage ([] (coverage (keys catalog))) ([iso3s] (let [have (filter catalog iso3s) missing (remove catalog iso3s)] {:requested (count iso3s) :covered (count have) :covered-jurisdictions (vec (sort have)) :missing-jurisdictions (vec (sort missing)) :note (str "cloud-itonami-iso3166-aus R0: " (count catalog) " seeded.")})))
(defn required-evidence-satisfied? [iso3 submitted] (when-let [{:keys [required-evidence]} (spec-basis iso3)] (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3] (when-let [sb (spec-basis iso3)] (when (:rep-owner-authority sb) (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3] (when-let [sb (spec-basis iso3)] (when (:corporate-number-owner-authority sb) (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))

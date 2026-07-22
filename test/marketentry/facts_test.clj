(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest sdn-has-spec-basis
  (let [sb (facts/spec-basis "SDN")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (= 2 (count (:required-evidence sb)))
        "thinner dossier than AGO's 4 and STP's 3 required-evidence items -- honest, not padded")))

(deftest sdn-has-no-rep-or-corporate-number-sub-map
  (testing "the dossier does not ground a distinct resident-rep regime or a separate tax-ID scheme -- unlike AGO"
    (is (nil? (facts/rep-spec-basis "SDN")))
    (is (nil? (facts/corporate-number-spec-basis "SDN")))))

(deftest sdn-legal-basis-is-the-2010-act-not-the-1978-regulations
  (testing "the current legal basis is the 2010 Act; the 1978/1995 regime is historical context only"
    (let [sb (facts/spec-basis "SDN")]
      (is (re-find #"2010" (:legal-basis sb)))
      (is (not (re-find #"^1978" (:legal-basis sb)))
          "must not state the superseded 1978 regulations as the primary legal basis"))))

(deftest sdn-does-not-leak-south-sudan-facts
  (testing "no South Sudan (SSD) fact leaks into the SDN catalog entry"
    (let [sb (facts/spec-basis "SDN")
          text (pr-str sb)]
      (is (not (re-find #"(?i)south sudan" text)))
      (is (not (re-find #"(?i)\bssd\b" text)))
      (is (not (re-find #"(?i)juba" text)))
      (is (nil? (facts/spec-basis "SSD")) "SSD must not be a registered jurisdiction in this catalog"))))

(deftest sdn-does-not-claim-an-e-procurement-portal
  (testing "no national e-procurement portal was verified for Sudan -- must not be invented"
    (let [sb (facts/spec-basis "SDN")]
      (is (re-find #"no verified national e-procurement portal" (:national-spec sb))))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "SDN")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "SDN" all)))
    (is (not (facts/required-evidence-satisfied? "SDN" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["SDN" "ATL"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

# Viikko 1 – Task App

## Teknologiat
- Kotlin
- Jetpack Compose
- Android Studio
- Android SDK (minSdk 24)

## Rakenne
Projektissa on selkeä vastuunjako:
- **domain**  
  - `Task` data class  
  - Puhtaat Kotlin-funktiot:
    - `addTask`
    - `toggleDone`
    - `filterByDone`
    - `sortByDueDate`
- **ui**  
  - Compose-näkymät (`HomeScreen`)
- **MainActivity**  
  - Kytkee Compose UI:n sovelluksen käynnistykseen

## Ominaisuudet
- Mock-data (5–10 tehtävää)
- Tehtävien lisääminen (Add)
- Tehtävän tilan vaihto (Done / Undone) klikkaamalla
- Tehtävien suodatus:
  - All
  - Done
  - Undone
- Tehtävien järjestäminen päivämäärän mukaan (uusin ensin)
- Selkeä Compose UI:
  - Column / Row / Modifier
  - Ei XML-layoutteja

## Käyttö
- **Add**: lisää uuden tehtävän listaan
- **Sort**: järjestää tehtävät eräpäivän mukaan (uusin ensin)
- **All / Done / Undone**: suodattaa näkyvät tehtävät
- **Klikkaa tehtävää** vaihtaaksesi sen tilan (Done ↔ Undone)

## APK
https://github.com/rekotornberg/native-week1/releases/tag/v1.0

## Sovelluksen ajaminen
1. Avaa projekti Android Studiossa
2. Valitse emulaattori tai fyysinen Android-laite
3. Paina **Run**

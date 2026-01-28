# Viikko 3 - MVVM

## Miksi MVVM on hyöyllinen Compose sovelluksissa?
- MVVM on arkkitehtuuri malli Model, View ja ViewModel. Tällä tavalla saadaan jaettua sovelluksen vastuut selkeästi eri kerroksiin.
- Sovelluksen rakenne pysyy siistinä, ja sitä on helppo laajentaa.
- Model, sovelluksen data ja tietorakenne
- View, hoitaa käyttöliittymää
- ViewModel, hallitsee sovelluksen tilat.

## Miten StateFlow toimii?
- StateFlow:ta käytetään tilanvälityksiin. StateFlow ylläpitää sovellusken tilaa ViewModelissa. Kun tila muuttuu, se lähettää uuden arvon tilaa kuunteleville funktioille.

# Viikko 2 - View model

## Miten Compose tilanhallinta toimii
- **Tilanhallinta**
  -Compose tilanhallinta perustuu käyttöliittymämalliin, missä käyttöliittymä kertoo sovelluksen nykyistä tilaa
  -Compose seuraa tilaa (stste) ja kun tilan arvo muuttuu, compose "uudelleenrakentaa" automaattisesti ne käyttöliittymän osat, jotka käyttää kyseistä tilaa. 

- **Miksi ViewModel on parempi kuin pelkkä remember**
  - ViewModel erottaa sovelluslogiikan käyttöliittymästä, koodi on selkeämpää ja helpommin testattavissa ja esim. remeberiä käyttäessä, tila häviää joissain tilanteissa (näytönkääntö)

# Viikko 1 – Task App

## Rakenne
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
- Mock data
- Tehtävien lisääminen (Add)
- Tehtävän tilan vaihto (Done / Undone) klikkaamalla
- Tehtävien suodatus:
  - All
  - Done
  - Undone
- Tehtävien järjestäminen päivämäärän mukaan

## Sovelluksen käyttö
- **Add**: lisää uuden tehtävän listaan
- **Sort**: järjestää tehtävät eräpäivän mukaan (uusin ensin)
- **All / Done / Undone**: suodattaa näkyvät tehtävät
- **Klikkaa tehtävää** Tehtävän tila vaihtuu done -> undone tai undone -> done

## APK
https://github.com/rekotornberg/native-week1/releases/tag/v1.0

## Sovelluksen ajaminen
1. Avaa projekti Android Studiossa
2. Valitse emulaattori tai Android laite
3. Paina **Run**

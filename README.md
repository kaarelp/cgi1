Lahenduskäigu kirjeldus:
1) kylastusajad.txt loetakse sisse ressursina `getResource()` meetodiga ning tehakse `Stream<String>`-ks `Files.lines()` meetodiga.

2) Stream<String> loetakse `Map<Integer, Integer>` andmestruktuuru sisse. See `Map<Integer, Integer>` andmestruktuur tähistab ühte ööpäeva mis on jagatud minutite järgi 1440-ks (24*60=1440). `Map<Integer, Integer>` andmestruktuuris `key` on ühe minuti järjekorranumber ning `value` on külastajate arv sellel minutil.
Kui `kylastusajad.txt` failis on rida `00:00,00:03` siis tehakse 3 sissekannet `Map<Integer, Integer>`-i:
`map.put(0, 1);`
`map.put(1, 1);`
`map.put(2, 1);`
st minutitel 0, 1 ja 2 viibib muuseumis 1 külastaja. Kui `kylastusajad.txt` failis on eelmisega kattuv rida näiteks `00:02,00:04` siis tehakse 2 sissekannet:
`map.put(2, 2);`
`map.put(3, 1);`
Indeks 2 kirjutati üle ning nüüd on sisestatud, et minutil 2 viibib muuseumis samaaegselt 2 külastajat.

3) `Map<Integer, Integer` andmestruktuurist otsitaks suurim `value` ehk suurim samaaegsete külastajate arv.

4) `Map<Integer, Integer` andmestruktuurist filtreeritaks evälja kõik sissekanded mis on selle leitud suurima külastajate arvuga.

5) Leitud `Map.Entry<Integer, Integer>` sissekanded teisendatakse `VisitationPeriod` objektideks mis hoiavad neid andmeid mugavamal kujul. `VisitationPeriod` objektides liidetakse kõrvuti olevad minutid kokku samasse objekti - nihutatakse perioodi alguse või lõpu aega vastavalt edasi nii et ta hõlmaks kõiki kõrvutiolevaid minuteid.

6) Leitakse nimekiri tulemustest `List<VisitationPeriod>` sest võimalik on selline juhtum, et päevas on 2 või enamat ajaliselt lahutatud perioodi kus on sama maksimaalne samaaegsete külastajate arv.

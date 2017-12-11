<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="" xml:lang="">
<head>
  <meta charset="utf-8" />
  <meta name="generator" content="pandoc" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes" />
  <!--[if lt IE 9]>
    <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
  <![endif]-->
</head>
<body>
<h1 id="wstęp">Wstęp</h1>
<h2 id="wprowadzenie-do-problemu">Wprowadzenie do problemu</h2>
<p>Efektywne zarządzenie rzeczywistym łańcuchem dostaw nieoderwalnie związane jest z szeregiem procesów planowania. Przed każdym przedsiębiorstwem produkcyjno-dystrybucyjnym staje zadanie takiego sterowania przypływem materiałów w sieci, by zapewnić możliwie wysoki poziom obsługi klienta przy jednoczesnym minimalizowaniu zapasów oraz kosztów związanych ze składowaniem i transportem materiałów. Są to jedne z wielu wskaźników efektywności (ang. KPI<a href="#fn1" class="footnote-ref" id="fnref1"><sup>1</sup></a>), które pozwalają ocenić to, czy łańcuch dostaw zarządzany jest w sposób zbliżony do optymalnego.</p>
<p>Ilość danych, które należy przetworzyć i przeanalizować procesie planowania sprawia, że nieodzowne jest wykorzystywanie zaawansowanych systemów komputerowych. Praktycznie niemożliwa jest jednak pełna automatyzacja tego procesu. Liczba wyjątków występująca ze względu na różnorodność produktów, zróżnicowana struktura łańcuchów dostaw w sieci, a także dynamika branży FMCG<a href="#fn2" class="footnote-ref" id="fnref2"><sup>2</sup></a> uniemożliwiają potraktowania problemu dystrybucji jako klasycznego zadania optymalizacyjnego.</p>
<p>Konieczne staje się zatem rozpoznanie typowych problemów, które pojawić mogę się na ścieżce planowania produkcji i dystrybucji artykułów FMCG, a także wypracowania określonych strategii mitygowania ryzyka możliwych do wdrożenia w środowisku biznesowym. Z oczywistych względów nie jest wskazane, aby rozwijać i wypracowywać rozwiązania takich problemów wyłącznie w sposób praktyczny. Niesie to ze sobą znaczące ryzyko poniesienia strat pieniężnych, a także obarczone jest koniecznością długiego oczekiwania na rezultaty podjętych decyzji. Z tego powodu autor niniejszej pracy zdecydował się na wykorzystanie symulacji komputerowej jako narzędzia do badań. Wiąże się to również z szeregiem innych korzyści – możliwości modelowania i odwzorowania dowolnych łańcuchów dostaw, czy przeprowadzania wielokrotnych testów i badania wrażliwości systemu na zmiany parametrów jego poszczególnych ogniw.</p>
<p>Jakość wyników symulacji komputerowej jest jednak silnie zależna od zaimplementowanego w niej modelu i jego zbieżności z rzeczywistością. Aby pozostać możliwie blisko realiów biznesowych w pracy wykorzystane zostaną dane historyczne rzeczywistego przedsiębiorstwa X funkcjonującego w branży FMCG. Składać się na nie będą dane dotyczące popytu i sprzedaży, struktura łańcuchów dostaw, a także ograniczenia związane z wytwarzaniem poszczególnych produktów.</p>
<h2 id="przegląd-literatury">Przegląd literatury</h2>
<p>Problem modelowania łańcucha dostaw oraz badania przepływu materiałów w sieci nie jest nowy. Powstało wiele publikacji wpisujących się w tę tematykę, jednak żadna z nich nie wyczerpuje dostatecznie tematu wykorzystywania symulacji komputerowej w celu odnalezienia dobrych praktyk w zarządzaniu łańcuchem dostaw. Co więcej, w publikacjach można zaobserwować pewną polaryzację. Pierwsza grupa prac to te skupione wokół tworzenia jak najdokładniejszych modeli matematycznych łańcucha dostaw, które pozbawione są kontekstu biznesowego. Przeciwnym biegunem są zaś publikacje magazynów branżowych, kładące akcent przede wszystkim na aspekty biznesowe, które dowodzą słuszności stawianych w nich tez poprzez odwołania do rzeczywistych przedsiębiorstw i wdrożonych w nich rozwiązań oraz efektu ich implementacji.</p>
<p><em>Modeling Suppy Chain Dynamics: A Multiagent Approach</em> opisuje podstawy tworzenia modeli łańcucha dostaw w oparciu elementy dwóch klas – strukturalnej (odzwierciedlającej ogniwa łańcucha) i kontrolnej (reprezentującej przepływ popytu, materiałów i informacji). O ile publikacja stanowi dobry punkt wyjściowy dla tworzenia symulacji w oparciu o system wieloagentowy, nie dostarcza przykładów ani propozycji implementacji, zaś sam problem omówiono pozbawiając go jakiegokolwiek podłoża biznesowego. Pozycja ta nie wyczerpuje zatem postawionego w rozdziale 1.1 problemu.</p>
<p><em>A multi-agent system for chemical supply chain simulation and management support</em> jest kolejną pozycją, która traktuje o modelowaniu łańcucha dostaw z wykorzystaniem MAS<a href="#fn3" class="footnote-ref" id="fnref3"><sup>3</sup></a> w oparciu o protokół komunikacyjny KQML<a href="#fn4" class="footnote-ref" id="fnref4"><sup>4</sup></a>. Choć omawiany jest tutaj problem dystrybucji artykułów chemicznych, wszelkie poczynione wnioski wspólne będą dla wszystkich łańcuchów dostaw, które charakteryzują się długim czasem reakcji na bodziec oraz koniecznością produkcji pojedynczej partii o wysokim wolumenie. Zawężenie dziedziny pracy niesie ze sobą jednak korzyść w postaci silnego oparcia o konkretny proces produkcyjny (technologiczny) w firmie o określonej i rzeczywistej strukturze. Dodatkowym atutem publikacji są również przykłady implementacji pomiędzy agentami z wykorzystaniem języka KQML. Praca wpisuje się więc mocno w postawiony w pracy problem, jednak poprzestaje na zagadnieniu tworzenia symulacji. Autorzy nie wykorzystują tworzonego modelu w celu wskazania i analizy konkretnych strategii zarządzania przepływem materiałów w sieci.</p>
<p><em>Supply chain simulation - a tool for education, enhancement and endeavor</em> jest publikacją, która w oparciu o Lean Leap Logistics Game dowodzi tezy, jako że wykorzystanie symulacji komputerowej do analizy modelu rzeczywistego przedsiębiorstwa może przynieść realne zyski. W pracy zaprezentowany i omówiony został spadek zapasów przedsiębiorstwa, który wynikał z zsynchronizowania ze sobą poszczególnych ogniw łańcucha dostaw. Efektem analizy modelu, a także symulacji komputerowej przeprowadzonej w oparciu o niego, było także zmniejszenie buforów czasowych występujących w transporcie oraz zwiększenie stabilność popytu. Pozycja ta, o ile nie przedstawia konkretnych, uniwersalnych i skalowalnych metod zarządzania łańcuchem dostaw, dowodzi użyteczności symulacji komputerowej jako narzędzia do rozwiązywania rzeczywistych problemów biznesowych.</p>
<p><em>Distributed Supply Chain Simulation Across Enterprise Boundaries</em> to publikacja, która porównuje ze sobą dwa różne podejścia do tworzenia symulacji łańcucha dostaw. Pierwsze z nich zakłada integrację symulacji ze produkcyjnym środowiskiem, w którym dostępne są informacje o przepływie materiałów w sieci. Zakłada więc stworzenie wysokopoziomowej aplikacji rozszerzającej możliwości systemu SCM<a href="#fn5" class="footnote-ref" id="fnref5"><sup>5</sup></a> zaimplementowanego w firmie. Drugie podejście, bardziej teoretyczne i klasyczne, opiera się o modelowanie fizycznej sieci dystrybucyjnej przedsiębiorstwa i odwzorowaniu go jako układu dyskretnego. Następnie dokonywane są badania modelu. Oba podejścia porównywane są sobą pod względem wydajności oraz interoperacyjności. Publikacja ta jest więc dobrym źródłem informacji na temat tego jakie podejście do symulacji należy wybrać w zależności od tego, co chcemy badać. Nie dotyka jednak zagadnienia optymalizacji elementów oraz struktury badanego łańcucha dostaw. Z uwagi na czas, który minął od stworzenia artykułu należy również z ograniczoną ufnością podejść do wyników dotyczących wydajności. Stacje robocze, na których przeprowadzone były badania (Sun UltraSparc II 250MHz) dysponują mocą obliczeniową o trzy rzędy wielkości niższą od komputera wyposażonego w procesor Intel Core i7 7700K.</p>
<p>Popełniony powyżej przegląd literatury wskazuje jednoznacznie na to, że choć istnieje wiele pozycji wpisujących się w nurt tworzenia symulacji komputerowych łańcucha dostaw, żadna nie wyczerpuje dostatecznie problemu w kontekście poszukiwania optymalizacji. Stąd też możliwe jest wyciągnięcie wniosku, jakoby niniejsza praca nie powielała rozwiązanych już problemów. Uzasadnia to zatem jej powstanie i nadaje jej cechy rozwiązującej określony problem badawczy.</p>
<h2 id="określenie-problemu-badawczego">Określenie problemu badawczego</h2>
<p>Po dokonaniu przeglądu literatury fachowej dostrzec można, że nie istnieją pozycje, które w bezpośredni sposób odnosiłyby się do tworzenia symulacji komputerowej w celu poszukiwania możliwych optymalizacji. Żadna z wymienionych w rozdziale 1.2 publikacji nie nawiązuje też do logiki powszechnie wykorzystywanych systemów planowania opartych o MRP<a href="#fn6" class="footnote-ref" id="fnref6"><sup>6</sup></a>. Istnieje zatem pewna luka, którą autor tej pracy pragnie wypełnić. Rzeczywiste przedsiębiorstwa sięgają po sprawdzone i skalowane rozwiązania takie jak – ogólnie pojęty – SAP<a href="#fn7" class="footnote-ref" id="fnref7"><sup>7</sup></a>. Ponieważ sposób planowania produkcji i dystrybucji oparty o SAP rządzi się określonymi prawami oraz ograniczeniami (omówionymi dokładnie w rozdziale 2.), symulację należy stworzyć w taki sposób by poczynione w pracy wnioski mogły znaleźć zastosowanie w biznesowej rzeczywistości.</p>
<p>Problem badawczy dotyczy zatem skonstruowania symulacji, która działa w oparciu mechanizmy MRP i uwzględnia predefiniowane w systemach planowania mechanizmy planowania produkcji i dystrybucji. Na stworzonym modelu, zasilanym rzeczywistymi danymi, przeprowadzane będą badania weryfikujące postawione w rozdziale 1.4 tezy. Rozwiązywanym problemem będzie zatem odpowiedź na pytanie w jaki sposób planować produkcję i dystrybucję (jakie strategie przyjmować) by optymalizować kluczowe wskaźniki efektywności sieci.</p>
<h2 id="cel-pracy">Cel pracy</h2>
<p>Celem niniejszej pracy jest zbadanie oddziaływania określonych parametrów związanych z planowaniem produkcji i dystrybucji na funkcjonowanie łańcucha dostaw. Zamiarem autora jest dokonanie analizy wybranego łańcucha dostaw na kilku płaszczyznach:</p>
<ul>
<li><p>Zbadanie wpływu parametrów odpowiedzialnych za generowanie replenishmentów<a href="#fn8" class="footnote-ref" id="fnref8"><sup>8</sup></a> (strategii, progów) na KPI łańcucha dostaw</p></li>
<li><p>Zbadanie zachowania łańcucha dostaw pod wpływem błędnego prognozowania sprzedaży oraz określenie zakresu możliwości reagowania na nieoczekiwany popyt</p></li>
<li><p>Zbadanie wpływu zdarzeń losowych takich jak kradzież towaru, uszkodzenia linii produkcyjnych czy rozbieżności pomiędzy danymi w systemach SCM a rzeczywistością na funkcjonowanie łańcucha dostaw</p></li>
</ul>
<p>Analiza wybranych łańcuchów dostaw przeprowadzona zostanie w oparciu o rzeczywiste dane i posłuży do wyciągnięcia zarówno generycznych jak i odnoszących się do konkretnego przypadku wniosków, jakie strategie zarządzania łańcuchem dostaw należy przyjąć, by parametry sieci zbliżyły się do optimum.</p>
<p>Z uwagi na mnogość scenariuszy możliwych do zbadania za pomocą symulacji oraz chęć rozszerzenia zakresu badań poza same parametry systemów planowania w pracy zweryfikowane zostaną następujące hipotezy:</p>
<table>
<tbody>
<tr class="odd">
<td>Możliwe jest dokonanie poprawy funkcjonowania łańcucha dostaw poprzez odpowiednie skalibrowanie parametrów systemów planowania.</td>
</tr>
</tbody>
</table>
<table>
<tbody>
<tr class="odd">
<td>Niska dokładność prognoz sprzedaży wprowadza do łańcucha dostaw istotne zaburzenia znacząco wpływające na KPI.</td>
</tr>
</tbody>
</table>
<table>
<tbody>
<tr class="odd">
<td>Symulacja komputerowa jest efektywnym sposobem analizowania łańcucha dostaw i umożliwia optymalizację rzeczywistych systemów</td>
</tr>
</tbody>
</table>
<h1 id="planowanie-produkcji-i-dystrybucji-w-środowisku-sap">Planowanie produkcji i dystrybucji w środowisku SAP</h1>
<h2 id="wstęp-model-mrp">Wstęp – model MRP</h2>
<p>Planowanie produkcji i dystrybucji w środowisku SAP opiera się o model MRP. Istnieje wiele standardów oraz implementacji tego modelu, jednak wspólne dla wszystkich ramy znajdują się na rysunku 1.</p>
<p>Rysunek - Model MRP, Źródło: Opracowanie własne</p>
<p>Model MRP opiera się na logice, w której pierwotnie określany jest generalny, ramowy plan funkcjonowania całego przedsiębiorstwa, a następnie, wraz z każdym krokiem, jest on uszczegóławiany aż do poziomu egzekucji produkcji poszczególnych dóbr. Występują w nim sprzężenia zwrotne, które zakładają, że plan idealny może być niemożliwy do osiągnięcia. W takim przypadku należy stworzyć plan, który możliwy będzie do realizacji po uwzględnieniu dostępnych zasobów – materiałowych, produkcyjnych, ludzkich. To właśnie ten element (RCCP<a href="#fn9" class="footnote-ref" id="fnref9"><sup>9</sup></a>) stanowi główną różnicę pomiędzy modelami MRP oraz MRP II.</p>
<p>Pierwszym krokiem w MRP jest planowanie wysokiego poziomu. To tutaj określane są cele biznesowe organizacji – strategia rozwoju, portfolio produktów, sposoby docierania do potencjalnych klientów, a także sposób pozyskiwania sprzedawanych dóbr. Wszystkie plany są generyczne i na tym etapie nie są uwzględniane żadne rzeczywiste ograniczania. Momentem przejścia na niższy poziom jest tworzenie planu strategicznego. Jest to ciągle planowanie na wysokim poziomie, jednak następuje tutaj rozbicie produktów na pewne rodziny dla których zakładane są określone wolumeny sprzedaży. To właśnie względem tych wolumenów tworzona będzie prognoza finansowa przychodów przedsiębiorstwa. Zakładane wielkości sprzedaży stanowią również bazę do kalkulacji wymaganych zasobów produkcyjnych.</p>
<p>Jeżeli analiza wysokiego poziomu wskazuje na to, że zakładane cele są możliwe do osiągnięcia (to znaczny – nie istnieją teoretyczne ograniczenia produkcji i dystrybucji, które mogłyby w oczywisty sposób uniemożliwić osiągnięcie zadanego poziomu sprzedaży) następuje przejście na poziom planowania szczegółowego. Właśnie na tym etapie model MRP dostarcza odpowiedzi na pytanie, co, kiedy i w jakiej ilości produkować/zamawiać. Tworzony jest więc plan replenishmentu idealnego. Jednocześnie na tym etapie występować będzie najwięcej ograniczeń, które wymagać będą dostosowania rzeczywistego planu produkcji/dostaw do teoretycznie idealnych zapotrzebowań materiałowych. Wraz ze wzrostem różnorodności oferowanych produktów oraz zaawansowaniem technologii ich wytwarzania, uwidacznia się pewna luka pomiędzy prostym modelem MRP a rzeczywistością. Ilość danych i potencjalnych scenariuszy do przeanalizowania zdecydowanie przerasta możliwości człowieka. Jednocześnie sam model MRP nie dostarcza odpowiedzi na pytanie jak powinno przebiegać RCCP. Z pomocą przychodzą jednak rozszerzenia SAP omówione dokładnie w rozdziale 2.2.2.</p>
<p>Planowanie szczegółowe nie ogranicza się wyłącznie do ilości, kolejności i momentu produkcji wyrobów gotowych. Należy zdać sobie sprawę z występowania wzajemnej zależności komponentów, półproduktów oraz produktów końcowych. Wielokroć to właśnie one są głównym ograniczeniem i jednoznacznie decydują o możliwości, bądź braku możliwości wykonania produkcji. Oczywistym sposobem przeciwdziałania wahaniom zapotrzebowań i dostaw jest wprowadzenie buforów, odpowiednio zdefiniowanych progów replenishmentu. Stanowią one często nie tylko dodatkowe zabezpieczanie, ale też kompensację rzeczywistych ograniczeń związanych z produkcją/dostawami.</p>
<p>Upewniwszy się, że stworzony plan produkcji jest odpowiedzią na realne zapotrzebowania i możliwy jest do wykonania z punktu widzenia dostępnych zasobów, konieczne jest przejście do trzeciego i ostatniego głównego bloku MRP – egzekucji i kontroli. Głównym celem jest weryfikacja tego, czy stworzony wcześniej (zgodny z teoretycznymi założeniami) plan wykonywany jest zgodnie z harmonogramem. Być może założona wydajność linii produkcyjnej czy stacji roboczej nie odpowiada rzeczywistości i wymaga korekty przy kolejnych kalkulacjach. Może zdarzyć się tak, że procent odpadów z produkcji przekracza założony próg i by wyprodukować wymaganą ilość pełnowartościowego produktu należy tak naprawdę brać pod uwagę zwiększone zapotrzebowanie na komponenty. Ostatecznie – teoretyczne zużycie materiałów obliczone przez inżyniera procesu może różnić się od rzeczywistego, co wymaga korekcji BOM<a href="#fn10" class="footnote-ref" id="fnref10"><sup>10</sup></a>. Potencjalnych scenariuszy może być wiele, jednak każde odstępstwo od teoretycznych założeń powinno zostać odnotowane, a sam model skorygowany w taki sposób, by następna kalkulacja zaowocowała dokładniejszymi i bliższymi prawdy obliczeniami.</p>
<p>Jak można się domyślić – cały proces MRP jest dynamiczny. Z uwagi na ilość czynników wpływających na określony efekt końcowy kalkulacje zapotrzebowań prowadzone są z co najmniej dobową częstotliwością. Możliwe byłoby oczywiście doprowadzenie do sytuacji, w której kalkulacja prowadzona byłaby w czasie zbliżonym do rzeczywistego, jednak wbrew pozorom nie jest to stan w pełni pożądany. Owocowałoby to brakiem spójności obrazu widzianego z różnych perspektyw – planowania popytu, planowania dystrybucji, planowania produkcji, planowania materiałowego. Dodatkowo należy wziąć pod uwagę tzw. efekt byczego bicza – małe zmiany na początku łańcucha zależności owocują bardzo dużymi wahaniami na jego końcu.</p>
<p>Dlatego właśnie większość rzeczywistych przedsiębiorstw definiuje interwały odświeżania składowych modelu MRP. W przypadku omawianej w kolejnych rozdziałach firmy X są one następujące:</p>
<ul>
<li><p>Plan strategiczny – 1 miesiąc</p></li>
<li><p>Prognoza popytu – 1 tydzień</p></li>
<li><p>Długoterminowy plan produkcji – 1 tydzień</p></li>
<li><p>Krótkoterminowy plan produkcji – 1 dzień</p></li>
</ul>
<h2 id="implementacja-modelu-mrp-w-sap">Implementacja modelu MRP w SAP</h2>
<p>Opisany w rozdziale 2.1 model MRP można w rzeczywistości w stosunkowo prosty sposób przekształcić do postaci, która umożliwi precyzyjne obliczanie zapotrzebowania materiałowego. Aby to uczynić należy określić przestrzeń elementów MRP oraz ich dodatni/ujemny wpływ na bilans kalkulacji. Istnieje tutaj pełna dowolność w kwestii notacji i jest to zależne od konkretnej implementacji SAP. W przypadku badanej firmy X wyróżnić można następujące elementy MRP:</p>
<p>Tabela - Wykaz dodatnich elementów MRP</p>
<table>
<thead>
<tr class="header">
<th><strong>Element</strong></th>
<th><strong>Pełna nazwa</strong></th>
<th><strong>Opis elementu</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>Stock</td>
<td>Stock</td>
<td>Zapas, fizycznie dostępny produkt spełniający określone wymogi jakościowe, który można zaoferować klientowi, bądź wykorzystać do produkcji wyższego rzędu</td>
</tr>
<tr class="even">
<td>PchOrd</td>
<td>Purchase Order</td>
<td>Potwierdzona dostawa produktu (pozostająca w fazie planowania), która fizycznie nie opuściła ogniwa zasilającego</td>
</tr>
<tr class="odd">
<td>ShipNt</td>
<td>Shipment Notification</td>
<td>Potwierdzona dostawa produktu, która fizycznie opuściła ogniwo zasilające i aktualnie znajduje się w tranzycie</td>
</tr>
<tr class="even">
<td>PlOrd</td>
<td>Planned Order</td>
<td>Zależnie od ogniwa – niepotwierdzona produkcja bądź zlecenie replenishmentu</td>
</tr>
<tr class="odd">
<td>PrcOrd</td>
<td>Process Order</td>
<td>Potwierdzona produkcja towaru, która zlecona została fabryce</td>
</tr>
<tr class="even">
<td>QMLot</td>
<td>Quality Inspection Lot</td>
<td>Wyprodukowany towar, który nie przeszedł kontroli jakości, nie można zaoferować go klientowi, bądź wykorzystać do produkcji wyższego rzędu</td>
</tr>
</tbody>
</table>
<p>Tabela - Wykaz ujemnych elementów MRP</p>
<table>
<thead>
<tr class="header">
<th><strong>Element</strong></th>
<th><strong>Pełna nazwa</strong></th>
<th><strong>Opis elementu</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>Safety Stock</td>
<td>Safety Stock</td>
<td>Próg replenishmentu – statyczna wartość, określająca docelową wartość bilansu w danym ogniwie łańcucha</td>
</tr>
<tr class="even">
<td>Order</td>
<td>Order</td>
<td>Niepotwierdzone zamówienie klienta, może ono nie mieć pokrycie w fizycznie dostępnym zapasie</td>
</tr>
<tr class="odd">
<td>Deliv</td>
<td>Delivery</td>
<td>Potwierdzone zamówienie klienta mające fizyczne pokrycie w zapasie, bądź zlecenie replenishmentu to ogniwa niższego rzędu</td>
</tr>
<tr class="even">
<td>DepReq</td>
<td>Dependent Requirement</td>
<td>Zapotrzebowanie zależne, wykorzystanie w produkcji wyższego rzędu</td>
</tr>
<tr class="odd">
<td>PlORel</td>
<td>Planned Order Release</td>
<td>Otrzymanie replenishmentu od ogniwa niższego rzędu</td>
</tr>
<tr class="even">
<td>IndReq</td>
<td>Independent Requirement</td>
<td>Prognoza sprzedaży</td>
</tr>
</tbody>
</table>
<p>Wymienione wyżej elementy tworzyły będą tak zwane MRP listy – czyli zestawienia odpowiednich ruchów materiałowych, które umożliwiają dokonania kalkulacji zapotrzebowania, tak by zapewniona była ciągłość i terminowość dostaw towarów.</p>
<p>Przeanalizujmy kilka przykładów MRP List:</p>
<p><img src="media/image2.png" style="width:5.45397in;height:4.8125in" /></p>
<p>Rysunek - MRP Lista produktu 1</p>
<p>Jest to wyjątkowo prosty przypadek. Obecny zapas produktu wynosi 147 [CS]. Każdego dnia spodziewana jest sprzedaż na poziomie 6-9 [CS] reprezentowana przez elementy Independent Requirement. Obecnie żaden z klientów nie zdecydował się na założenie zamówienia – nie są spodziewane są więc żadne wysyłki – stąd brak elementów typu Order czy Delivery. Nie są spodziewane również żadne zasilenia ogniw niższego rzędu, co sugeruje brak elementów PlORel. Możliwe do zauważenia jest natomiast zasilenie z lokacji 5053. Co istotne - występuje przed momentem, gdy projekcja stanu magazynowego osiągnie poziom 0. Można więc określić próg repelnishmentu dla tego produktu. Wynosi on około 50 [CS]. Zwrócić uwagę należy również na wielkość samego zasilenia, która wynosi 100 [CS]. Nie jest to oczywiście wartość przypadkowa. W logice SAP dla każdego produktu określana jest rozdzielczość replenishmentu – minimalna ilość produktu, która może być dostarczona z ogniwa wyższego rzędu. W tym przypadku wynosi ona 100 [CS]. Nie wynika ona z żadnej kalkulacji, a jest odzwierciedleniem biznesowych realiów. W tym konkretnym przypadku do lokacji A751 możemy dostarczyć minimum jedną paletę, a dla produktu 83725610 oznacza to równowartość 100 kartonów [CS].</p>
<p><img src="media/image3.png" style="width:5.51042in;height:4.41283in" /></p>
<p>Rysunek - MRP Lista produktu 2</p>
<p>Również i w tym przypadku mamy do czynienia ze stosunkowo mało skomplikowaną sytuacją. W pierwszej kolejności uwagę należy zwrócić na obecność elementów Quality Inspection Lot oraz Process Order. Sugerują one od razu, że lokacja 2725 jest w rzeczywistości fabryką. Ponieważ oba te elementy występują jednocześnie (tzn. z tą samą datą) wyciągnąć można wniosek, że zlecona została produkcja 1452 [CS] produktu. W chwili generowania listy wyprodukowano już 396 [CS], ciągle odbywa się produkcja pozostałych 1056 [CS]. Wyprodukowane 396 [CS] nie są jednak możliwe do wykorzystania do wysyłki lub sprzedaży, ponieważ nie została przeprowadzona kontrola jakości produktów – nie są one jeszcze zapasem. 10.08 zaplanowana jest kolejna produkcja. Ponieważ nie została jeszcze ona zlecona, a jedynie wstępnie zaplanowana, reprezentowana jest przez MRP element, którym jest Planned Order.</p>
<p>Omówioną uprzednio produkcję zlecono ponieważ do fabryki spływają zlecenia replenishmentów z ogniwa niższego rzędu – lokacji 2621. Jest to jednocześnie jedyna lokacja, która wymaga zasilenia.</p>
<p>Należy również zwrócić uwagę na projekcje stanu magazynowego. Przed produkcją 10.08 wynosi ona aż -3036 [CS]. Potrzeby zasilenia ogniwa niższego rzędu znacznie przewyższają więc zaplanowaną produkcję. Konieczna jest zatem taka korekta planu produkcyjnego, by zaspokoić wszystkie zapotrzebowania.</p>
<p><img src="media/image4.png" style="width:5.51042in;height:4.65458in" /></p>
<p>Rysunek - MRP lista produktu 3</p>
<p>Jest to przypadek zdecydowanie bardziej skomplikowany niż dwa poprzednie. W momencie generowania MRP listy do dyspozycji mamy 347 [CS]. Kolejne 324 [CS] znajdują się w tranzycie – odzwierciedlone jest to za pomocą elementu Shipment Notification. Ogniwem zasilającym jest 2621.</p>
<p>Zarówno obecnie dostępny zapas jak, produkty w tranzycie, jak i przyszłe replenishmenty wykorzystywane zostaną do produkcji wyższego rzędu. Zdradza to obecność zapotrzebowania zależnego. Część z tej produkcji została już zlecona (potwierdzone zapotrzebowania zależne – Order Reservation), część produkcji pozostają nadal w fazie planowania (Dependent Requirement).</p>
<p>Aby zaspokoić przyszłe zapotrzebowania zależne występujące w lokacji 2727 konieczne jest uzupełnienie zapasu – stąd wygenerowane są kolejne zasilenia z lokacji 2621. Jak widać – w żadnym momencie projekcja stanu magazynowego nie jest mniejsza niż 0. Oznacza to, występuje równowaga pomiędzy występującymi zapotrzebowaniami i zasileniami.</p>
<p>Nie jest również prognozowana sprzedaż produktu w lokacji 2727 – nie występują żadne zapotrzebownia niezależne (Independent Requirement). Z tego powodu nie jest konieczne definiowanie progu replenishmentu buforującego wahania zapotrzebownia i dostaw.</p>
<h2 id="rozszerzenia-sap-udoskonalające-planowanie-mrp">Rozszerzenia SAP udoskonalające planowanie MRP</h2>
<h3 id="ograniczenia-logiki-mrp">Ograniczenia logiki MRP</h3>
<p>W rozdziale 2.1 omówione zostało dokładnie działanie modeli MRP oraz MRP II. Różnica pomiędzy nimi polegała głównie na RCCP, czyli uwzględnianiu ograniczonych możliwości produkcyjnych oraz dystrybucyjnych przy tworzeniu planu replenishmentów. Niestety sam proces RCCP, zasady wybierania priorytetów a także metody i algorytmy balansowania dostaw nie zostały w żaden sposób określone w MRP. Z pomocą nie przychodzi również sama implementacja MRP w SAP. Wszystkie procesy podejmowania decyzji przeniesione zostały na użytkowników systemu. Jest to luka, która uwydatnia się w szczególności w przypadkach bardzo ograniczonych możliwości dostaw oraz względnie wysokiego zapotrzebowania – kiedy stosunek <span class="math inline"><em>C</em> : <em>D</em> ≤ 1</span>. Sytuacje te wymagają bardzo dużej ilości ręcznych interwencji w plan dostaw generowany przez MRP, co znacząco pomniejsza zyski płynące z wdrożenia SAP.</p>
<p>Kolejnym bardzo istotnym ograniczaniem MRP jest brak możliwości zróżnicowania klas zapotrzebowania. Wszystkie elementy MRP mają dokładnie taką samą wagę i taki sam priorytet. Nie oddaje to w żaden sposób biznesowych realiów, gdzie np. zamówienie klienta nieznajdujące pokrycia w zapasie będzie zdecydowanie ważniejsze niż zapotrzebowanie zależne produktu, czy zapotrzebowania dystrybucji z ogniw niższego rzędu. W szczególności , gdy replenishmenty potrzebne są jedynie, by osiągnąć parametry bezpieczeństwa. Nastręcza to problemów zarówno podczas planowania produkcji, jak i dystrybucji (kiedy dzielimy produkty pomiędzy ogniwami niższego rzędu, gdzie źródła powstawania zapotrzebowani mogą być znacząco różne).</p>
<p>Trzecim głównym ograniczeniem logiki MRP jest brak możliwości kontrolowania przepływu materiałów w sieci. Jest to problem, który dotyczyć będzie przede wszystkim sieci i złożonej strukturze, w której występują wielopoziomowe replenishmenty. W takich przypadkach MRP nie posiada informacji na temat tego, w których ogniwach sieci najkorzystniej jest posiadać zapas. Kryteria wyboru mogą być różne (np. faktyczny koszt magazynowania towarów, czy też znacząco różniące się prognozy sprzedaży w poszczególnych ogniwach sieci), jednak niezależnie od podłoża, logika MRP nie posiada mechanizmu wspierającego podejmowanie takich decyzji.</p>
<p>Wymienione w tym rozdziale ograniczania nie wyczerpują rzecz jasna problemu niedoskonałości MRP. Potencjalne problemy można by zapewne znaleźć analizując konkretne problemy biznesowe. Przytoczone jednak luki wspólne są dla wszystkich systemów opartych o MRP niezależnie od obecnych tam procesów, czy rodzaju prowadzonego biznesu.</p>
<p>Można byłoby w tym momencie poczynić wniosek, jakoby rozważane w tym rozdziale ograniczenia eliminowały MRP (oraz jego implementację w SAP) jako skuteczną metodę planowania nowoczesnego przedsiębiorstwa produkcyjno-dystrybucyjnego. Należy jednak powstrzymać się od zbyt szybkiej oceny i zdać sobie sprawę, że wdrożenie jakiegokolwiek oprogramowania klasy ERP<a href="#fn11" class="footnote-ref" id="fnref11"><sup>11</sup></a> wymaga bardzo dużych nakładów finansowych i czasowych. Częstokroć implementacja tego typu rozwiązań nieodzownie związana jest z modyfikacją obecnie istniejących procesów, ich eliminacją, czy też zastąpieniem nowymi procedurami. Podobnie sam proces integracji zaburza efektywność działania przedsiębiorstwa. Dlatego właśnie dużo bardziej rozsądnym jest uzupełnienie istniejącego już rozwiązania o rozpoznane braki, niż tworzenie oraz implementacja zupełnie nowego oprogramowania.</p>
<p>W rozdziałach 2.3.2 oraz 2.3.3 omówione zostaną 2 rozwiązania, które eliminują omówione wcześniej ograniczenia. Mogą to być rozwiązania dostarczone bezpośrednio przez SAP, dostosowane do potrzeb konkretnego przedsiębiorstwa, jak i produkty zewnętrznych dostawców zintegrowane z SAPem.</p>
<h3 id="aps">APS</h3>
<p>Główne ograniczenia MRP w kontekście planowania produkcji dotyczyły braku algorytmów balansowania oraz nadawania priorytetów produkcji, a także konieczność rozpatrywania zbiorczych zaporzebowań w sieci zamiast aktualnego stanu każdego z węzłów. Odpowiedź na ten problem stanowi oprogramowanie klasy APS<a href="#fn12" class="footnote-ref" id="fnref12"><sup>12</sup></a>. Jest ono całkowicie zintegrowane z SAP. Za pomocą interfejsu pobiera dane na temat zapasów oraz zapotrzebowań. Posiada zdefiniowane koszty produkcji. Dysponuje również odpowiednio skonstruowanym systemem kar – za całkowite wyczerpanie zapasu, projekcję zapasu poniżej progu replenishmentu, a także projekcję zapasu powyżej maksymalnego dopuszczalnego zapasu. Działanie systemu APS opiera się więc na programowaniu liniowym oraz wielowymiarowym minimalizowaniu kar.</p>
<p>Uzupełnieniem warstwy algorytmicznej jest możliwość implementacji rozbudowanego graficznego interfejsu użytkownika, co niemożliwe jest w natywnym środowisku SAP. Wbrew pozorom GUI nie stanowi jedynie estetycznego uatrakcyjnia aplikacji, ale może znacząco rozszerzać funkcjonalność np. poprzez wyświetlenie BOM dla każdej z zaplanowanych produkcji po jej podświetleniu, zmianę harmonogramu produkcji za pomocą metody przeciągnij i upuść, czy informowaniu o potencjalnych brakach materiałowych za pomocą komunikatów pop-up.</p>
<p>Główne korzyści płynące z wdrożenia sytemu APS:</p>
<ol type="1">
<li><blockquote>
<p><strong>DC Network</strong> - możliwość planowania produkcji uwzględniając zapotrzebowania każdego ogniwa w sieci osobno, nie zaś rozważając zbiorcze zapotrzebowania występujące w ogniwie zasilającym.</p>
</blockquote></li>
<li><blockquote>
<p><strong>Minimum Cost Flow</strong> - możliwość przyznawania pierwszeństwa wybranym ogniwom w sieci na podstawie zróżnicowanego systemu kar za wykraczanie poza pożądane okno zapasu.</p>
</blockquote></li>
<li><blockquote>
<p>Balansowanie produkcji na poziomie dnia/tygodnia/miesiąca, możliwość planowania produkcji z wyprzedzeniem, kiedy spodziewane są problemy z dostępnością zasobów produkcyjnych w przyszłości (tzw. prebuild).</p>
</blockquote></li>
<li><blockquote>
<p><strong>GUI -</strong> dynamiczna informacja o brakujących komponentach, zasobach, wykorzystaniu maszyn.</p>
</blockquote></li>
<li><blockquote>
<p>Możliwość przeprowadzania analiz what-if w celu oceniania różnych potencjalnych scenariuszy planowania produkcji.</p>
</blockquote></li>
</ol>
<h3 id="apo">APO</h3>
<p>Moduł APO<a href="#fn13" class="footnote-ref" id="fnref13"><sup>13</sup></a> odpowiada za udoskonalone planowanie dystrybucji. Jego główną różnicą względem planowania MRP jest tworzenie dwóch równoległych planów replenishmetnów generowanych za pomocą dwóch silników:</p>
<ol type="1">
<li><blockquote>
<p><strong>Optimizer</strong> – tworzy plan w krótkim horyzoncie (6 tygodni), oparty jest na ograniczonych zasobach i reguluje przepływ materiałów w sieci poprzez minimalizację kosztów: magazynowania produktu, niedostępności produktu w danym ogniwie, opóźnienia replenishmentu.</p>
</blockquote></li>
<li><blockquote>
<p><strong>Heuristic</strong> – tworzy plan w długim horyzoncie (18 miesięcy), oparty jest on na nieograniczonych zasobach i odzwierciedla idealny plan replenishmentu. Jest więc on dokładnym odzwierciedleniem planowania materiałowego MRP.</p>
</blockquote></li>
</ol>
<p>Plan generowany przez Optimizera jest więc wykorzystywany podczas planowania wysyłek, zasileń ogniw niższego rzędu, zaś plan stworzony przez Heuristic będzie stanowić bazę do planowania produkcji. APO, podobnie jak APS wymaga zdefiniowania kosztów (kar) za magazynowanie produktu w danym węźle sieci, a także wykraczania poza zdefiniowane okno zapasu. Tworzenie planu dystrybucji polega więc na minimalizowania kosztów w sieci.</p>
<p>Główne korzyści płynące z wdrożenia APO:</p>
<ol type="1">
<li><blockquote>
<p><strong>Invetory Assignment Tool</strong> – narzędzie agregujące wszystkie informacje niezbędne do podjęcia decyzji o zaplanowaniu transferu materiałów pomiędzy węzłami sieci: nieograniczony plan dystrybucji, ilość zamówień i prognoza sprzedaży w węźle do którego planowana jest dostawa, ilość produktu w całej sieci, zaplanowane kolejne produkcje/dostawy, priorytet dostarczenia produktu oraz – co najważniejsze – sugerowana wielość oraz data wysyłki.</p>
</blockquote></li>
<li><blockquote>
<p><strong>Coverage Report</strong> – projekcja stanu magazynowanego produktu we wszystkich ogniwach w sieci uwzględniająca ograniczone zasoby produkcji/dystrybucji, która pozwala wychwycić oraz zmitygować potencjalne ryzyko niedostępności produktów.</p>
</blockquote></li>
<li><blockquote>
<p><strong>Planning Book</strong> – podsumowanie zamówień, zapotrzebowań, replenishmentów które pozwala dokonywać analiz projekcji stanu magazynowego oraz analiz what-if w zdefiniowanych agregacjach czasowych: dobowych, tygodniowych, miesięcznych.</p>
</blockquote></li>
<li><blockquote>
<p><strong>Interchangeability</strong> – narzędzie umożliwiające łącznie par produktów PIPO<a href="#fn14" class="footnote-ref" id="fnref14"><sup>14</sup></a>, by możliwe było planowanie pary produktów w taki sam sposób, jak gdyby były one jednym produktem. Minimalizuje to ilość pozostałości na produkcie, który wycofywany jest ze sprzedaży i umożliwia opóźnienie produkcji nowego produktu, co przekłada się na mniejszą ilość zapasu w sieci, bez stwarzania ryzyka niedostępności produktu.</p>
</blockquote></li>
<li><blockquote>
<p><strong>Time Phased Safety</strong> – funkcjonalność umożliwiająca tworzenie profili bezpieczeństwa zależnych od czasu. Nie stanowią one w takim wypadku integralnej części tzw. danych stałych i umożliwiają stosowanie różnych strategii bezpieczeństwa (np. podniesienie progu replenishmentu o 20% podczas okresu wakacyjnego), zależnych od bieżących potrzeb.</p>
</blockquote></li>
<li><blockquote>
<p><strong>Integracja z Business Warehouse</strong> (SAP BO) – archiwizacja i zapewnienie dostępu do kalkulacji przeprowadzonych w przeszłości, tak by możliwe było przeprowadzanie analiz z ich wykorzystaniem.</p>
</blockquote></li>
</ol>
<h2 id="powiązania-pomiędzy-mrp-sap-a-stworzoną-symulacją-komputerową">Powiązania pomiędzy MRP, SAP a stworzoną symulacją komputerową</h2>
<p>Celem niniejszej pracy nie jest wyłącznie odwzorowanie samego modelu MRP, jego ulepszenie, czy też integracja MRP z modułami klasy APO/APS. W najlepszym przypadku implementacja taka zaowocowałaby stworzeniem kopii opisanych w rozdziale 2. rozwiązań To w oczywisty sposób nie umożliwiłoby weryfikacji postawionych w rozdziale 1.4. tez. Dlatego właśnie stworzona na potrzeby tej pracy symulacja komputerowa (choć oparta o MRP i dziedzicząca po SAP logię kalkulacji) służyć będzie przede wszystkim badaniu zjawisk i zależności w łańcuchu dostaw.</p>
<p>Model MRP będzie więc jedynie punktem wyjściowym dla szerszego i bardziej skomplikowanego problemu, jakim jest efektywne zarządzanie łańcuchem dostaw. Dużo łatwiej powielić dobre praktyki, które powstały w oparciu o istniejące i sprawdzone rozwiązania, niż te, które wypracowane zostały wyłącznie na podstawie teoretycznych rozważań niekoniecznie znajdujących odzwierciedlenie w rzeczywistości. Jeżeli zaś sam model zasilony zostanie rzeczywistymi danymi dotyczącymi prognozowanej sprzedaży i faktyczną sprzedażą możliwe będzie wyciągnięcie znaczących oraz wiarygodnych wniosków.</p>
<p>Co pozostanie jednak bez zmian to sama logika kalkulacji MRP wraz z jej ograniczeniami, nomenklatura samych elementów MRP omówionych w rozdziale 2.2., a także sposób przeglądania wyników kalkulacji zgodny z konwencją MRP Listy w SAP.</p>
<p>Możliwe byłoby w tym momencie dokonanie pewnej krytyki implementowanego rozwiązania. Jeżeli powszechnie dostępne są pewne komercyjne rozwiązania umożliwiające minimalizację lub wręcz eliminację niedoskonałości modelu MRP, jaki sens ma przeprowadzanie kalkulacji i wyciąganie wniosków w oparciu o symulację komputerową pozbawioną cech systemów APO lub APS? Jest to pytanie co najmniej słuszne, lecz odpowiedź na nie jest prosta. Systemy klasy APO/APS rozszerzają możliwości MRP, lecz nie zastępują go zupełnie. Istnieje ponadto wiele procesów, których rozwiązania te nie są w stanie pokryć. Najlepszym przykładem może być długoterminowe planowanie materiałowe, które w wielu rzeczywistych przedsiębiorstwach ciągle przeprowadzane jest ręcznie, tylko i wyłącznie na podstawie list MRP. Tak więc możliwe staje się wyciągnięcie dosyć prostego wniosku, opartego na powszechnej w informatyce zasadzie GI-GO<a href="#fn15" class="footnote-ref" id="fnref15"><sup>15</sup></a> - jeżeli systemy rozszerzające funkcjonalności MRP zasilane są danymi niskiej jakości, również i one generować będą nie znaczące tak wiele rezultaty. Co jest również istotne – wiele generycznych wniosków dotyczących zarządzania łańcuchem dostaw poczynionych w wyniku rzeczonej symulacji będzie mogło znaleźć zastosowanie w przypadku przedsiębiorstw wykorzystujących APO/APS.</p>
<h1 id="modelowanie-łańcucha-dostaw">Modelowanie łańcucha dostaw</h1>
<h2 id="model-odzwierciedlony-w-symulacji">Model odzwierciedlony w symulacji</h2>
<p>Zaimplementowany w symulacji model odpowiada sieci dystrybucyjnej przedsiębiorstwa X. Składa się z 9 ogniw, które reprezentują fizyczne miejsca składowania dóbr. Przepływ dóbr w sieci pomiędzy parą dwóch dowolnych ogniw jest wyłącznie jednokierunkowy. Wyłącznie jedno ogniwo w całej sieci to tzw. customer facing DC<a href="#fn16" class="footnote-ref" id="fnref16"><sup>16</sup></a> (Rysunek 5, sygnatura 2621). Produkty wytwarzane są w czterech fabrykach (Rysunek 5, sygnatury 5053, 4850, 4853, 2725). Wszystkie dobra importowane poddawane są kontroli celnej i składowane są w magazynie celnym (Rysunek 5, sygnatura 2751). Dodatkowo zasilenia z niektórych fabryk nie odbywają się bezpośrednio, lecz z przeładunkiem w pośrednim centrum dystrybucyjnym (Rysunek 5, sygnatura 9979). Ostatnim elementem w sieci są dwa centra manipulacji (Rysunek 5, sygnatury 2727 oraz 2742), w których świadczone są VAS<a href="#fn17" class="footnote-ref" id="fnref17"><sup>17</sup></a>. Do nich trafiają gotowe produkty, które podlegają manipulacji i wracają do głównego centrum dystrybucyjnego już jako inne dobra.</p>
<p>Na opisanym w tym rozdziale modelu prowadzone będą wszystkie badania, jednak nie oznacza to w żadnym wypadku, że stworzona aplikacja posiada ten model twardo zapisany w kodzie. Wręcz przeciwnie – wszystko konfigurowalne jest z poziomu użytkownika, a zaimplementowane algorytmy gwarantują poprawność obliczeń dla dowolnego modelu sieci.</p>
<p><img src="media/image5.png" style="width:5.05208in;height:2.83864in" /></p>
<p><span id="_Ref498852285" class="anchor"><span id="_Ref498852174" class="anchor"></span></span>Rysunek - Struktura badanego łańcucha dostaw</p>
<h3 id="elementy-modelu">Elementy modelu</h3>
<p>W omawianym modelu można wyróżnić cztery główne elementy:</p>
<ol type="1">
<li><p>Lokacje (<em>LOCATIONS</em>)</p></li>
<li><p>Produkty (<em>PRODUCTS</em>)</p></li>
<li><p>Ścieżki transportowe (<em>TLANES</em>)</p></li>
<li><p>Parametry bezpieczeństwa (<em>SAFETIES</em>)</p></li>
</ol>
<p>Zbiór lokacji określa ogniwa łańcucha dostaw. Jest to generyczna kategoria opisująca wszystkie węzły w sieci dystrybucyjnej, niezależnie od ich kategorii. Każda lokacja posiada unikalną sygnaturę (<em>plantcode</em>), która jest jej wyróżnikiem. Aby lokacje były łatwo rozróżnialne dla użytkownika, do każdej lokacji można przypisać również opis (<em>desceription</em>), w którym zawarte mogę być dodatkowe informacje takie jak np. nazwa fabryki, czy centrum dystrybucyjnego. Konieczne jest również określenie typu lokacji (<em>type</em>), który jednoznacznie odpowiada na pytanie, czy dana lokacja jest węzłem wytwarzającym dobra (typu <em>P</em>), czy węzłem przesyłającym je dalej, lub miejscem ich konsumpcji (w obu przypadkach – typ <em>F</em>).</p>
<p>Zbiór produktów określa przestrzeń dóbr przepływających przez sieć dystrybucyjną. Każdy z nich posiada unikalną sygnaturę (<em>gcas</em>), który pozwala na jednoznaczną identyfikację produktu podczas wszystkich przepływów informacji występujących wewnątrz przedsiębiorstwa. Nie musi on jednocześnie być wykorzystywany przez klientów, którzy znacznie częściej opierają się na kodach kreskowych (EAN<a href="#fn18" class="footnote-ref" id="fnref18"><sup>18</sup></a>). Każdy produkt posiada również opis (<em>description</em>), który ułatwia jego identyfikację. Może on np. zawierać nazwę handlową dobra, rozmiar produktu, ilość opakować w jednostce itd. Są to informacje zupełnie zbędne z punktu widzenia planowania zapotrzebowania materiałowego, jednak bardzo przydatne z punktu widzenia operatora systemu. Kolejną istotną cechą produktu jest jego jednostka miary (<em>uom</em>), która określa jak mierzmy ilość produktu (czy jest to kilogram, sztuka, paleta itp.). Konieczne jest również określenie typu dobra (<em>type</em>), by możliwe było zidentyfikowanie, czy jest to wyrób gotowy (<em>FERT</em>), półprodukt (<em>HALB</em>), materiał surowy (<em>RAW</em>), czy też opakowanie (<em>PACK</em>). Determinuje to również pozycję w hierarchii wykonywanych obliczeń. Najpierw kalkulowane są zapotrzebowania niezależne (czyli produktów końcowych), a dopiero później zależne (czyli pochodne planowanej produkcji i dystrybucji). Ostatnią rozpatrywaną cechą produktu jest jego wielkość zaokrąglania (<em>roundval</em>). Określa jednocześnie minimalną ilość produktu, którą można przesłać pomiędzy ogniwami w sieci, a także wielkość replenishmentu (który jest zawsze jej wielokrotnością). Konieczność jej stosowania wynika z fizycznych i biznesowych ograniczeń – np. braku możliwości przewiezienia ilości mniejszej niż jedna paleta.</p>
<p>Kolejnym elementem modelu są ścieżki transportowe. Określają one relacje pomiędzy poszczególnymi ogniwami w sieci – ogniwo początkowe (<em>startloc</em>) oraz ogniwo docelowe (<em>endloc</em>). Zwarte są tutaj również informacje na temat czasu trwania transportu pomiędzy ogniwami (<em>duration</em>) oraz fizycznej odległości pomiędzy nimi (<em>distance</em>).</p>
<p>Ostatnim, ale niekoniecznie najmniej ważnym, elementem zaimplementowanego modelu są parametry bezpieczeństwa. Rozumieć je należy jako parametry definiujące progi replenishmentu dla każdej pary (<em>product, location</em>). Zasadniczo rozróżnić możemy dwie strategie generowania zasileń. Statyczną, na podstawie stałej, zadanej wielkości oraz dynamiczną, która gwarantuje pokrycie na <em>n</em> jednostek czasu, niezależnie od wielkości zapotrzebowań występujących w tak określonym oknie czasowym. W stworzonym modelu znajduje to za odzwierciedlenie w dwóch parametrach. Strategia (<em>strategy</em>), informacje nas w jaki sposób generowane będą zasilenia, a pokrycie (<em>quantity</em>) odpowiada na pytanie poniżej jakiego progu wymagany będzie replenishment, albo na jak długo w przyszłość chcemy zapewnić pokrycie materiałowe.</p>
<h3 id="założenia-upraszczające-model">Założenia upraszczające model</h3>
<p>Pomimo, że każda tworzona symulacja komputerowa z natury zakładać powinna implementację modelu pozwalającego na uzyskanie jak najdokładniejszych wyników, niektóre zjawiska istniejące w rzeczywistości są trudne (czy też niemożliwe) do odtworzenia podczas procesu modelowania.</p>
<p>Jednym z najpoważniejszych problemów występujących w łańcuchach dostaw jest wspominany w rozdziale 2.1 efekt byczego bicza – nawet małe zaburzenia występujące na początku łańcucha dostaw mogą doprowadzić do bardzo dużych zaburzeń na jego końcu. Źródeł takich zaburzeń może być wiele – począwszy od niedokładnej prognozy sprzedaży, poprzez opóźnienia w załadunku/rozładunku pojazdów, uszkodzenie towaru w magazynie, katastrofę naturalną, spóźnienie się auta na prom, skończywszy na promocji dla konsumentów, która nie była komunikowana przez klientów i idące za tym zwiększone zapotrzebowanie materiałowe.</p>
<p>Choć z technicznego punktu widzenia możliwe jest zasymulowanie dowolnego z tych zjawisk, sporne jest czynienie tego bez określonego kontekstu biznesowego. Dlatego w stworzonym modelu rozpatrywane nie będą elementarne źródła takich zakłóceń. Zebrane one zostaną w grupę np. opóźnionej dostawy towaru, albo niedostępności towaru pomimo wcześniejszej awizacji. Dla tak generycznie określonych problemów badanie będzie zachowanie się modelu. Podobnie również wrażliwość modelu badana będzie dla zgeneralizowanych czynników.</p>
<p>Kolejnym uproszczeniem jest rozdzielczość czasu. W rzeczywistych przypadkach częstokroć godziny decydują o powodzeniu lub braku powodzenia określonego przedsięwzięcia, wpływając jednocześnie na wskaźniki efektywności. Co więcej – istnieją dedykowane systemy takie jak TMS <a href="#fn19" class="footnote-ref" id="fnref19"><sup>19</sup></a>lub YMS<a href="#fn20" class="footnote-ref" id="fnref20"><sup>20</sup></a>, zastosowanie których pozwala z bardzo wysoką precyzją zarządzać poszczególnymi slotami rozładunkowymi/załadunkowymi i planować wykorzystanie infrastruktury magazynowej w oparciu o awizacje. Również i w takim przypadku występować mogą wspomniane wcześniej zaburzenia, które w istotny sposób wpływają na cały łańcuch dostaw. Nietrudno wyobrazić sobie, wcale nierzadką, sytuację, w której magazyn pracuje wyłącznie w trybie dwuzmianowym, a spóźniony transport dociera do magazynu w piątek, poza godzinami pracy, gdzie kierowca będzie zmuszony oczekiwać na rozładunek przez 2 dni. Przełożenie tej sytuacji na dostępność towaru i możliwość realizacji zamówień jest oczywiste. Sytuacje jednak te nie zostały jednak dokładnie odwzorowane w symulacji. Minimalną jednostką czasu, na jakiej operuje symulacja jest pełna godzina. Wszystkie awizacje, procesy produkcyjne, zwolnienia z kontroli jakości itp. są dokładnie opisane przez czas, kiedy występują. Nie jest jednak dokonywana analiza tego czasu na poziomie niższym niż godzinowy. Nie jest to wymagane z perspektywy potencjalnego czasu pojedynczego przebiegu symulacji, który trwać może kilka miesięcy.</p>
<p>Poczynione uproszczenia wynikają z tego, że przedmiotem tej pracy nie pozostaje optymalizacja wszystkich procesów w łańcuchu dostaw, ani też zasymulowanie wszystkich możliwych rzeczywistych przypadków. Zgodnie z postawionymi w rozdziale 1.4 tezami badany jest wpływ parametrów odpowiedzialnych za planowanie na wybrana wskaźniki efektywności. Oznacza to, że w zakresie rozważań pozostaje odpowiedzenia na pytanie takie jak ‘w którym ogniwie łańcucha dostaw optymalne jest posiadanie zapasu?’, ‘jakie parametry bezpieczeństwa należy utrzymywać, by uzyskać pożądany poziom obsługi klienta?’, ‘jak rozmiar minimalnego wolumenu produkcyjnego wpływa na ilość towaru w łańcuchu dostaw?’, bądź też ‘jakie przełożenie na funkcjonowanie łańcucha dostaw ma prognoza sprzedaży o określonej wartości MAPE<a href="#fn21" class="footnote-ref" id="fnref21"><sup>21</sup></a>?’. Aby znaleźć odpowiedzi na te pytania konieczne jest przeprowadzenie generycznej i wysokopziomowej analizy łańcucha dostaw. Z uwagi na czas tranzytu pomiędzy ogniwami w badanym modelu, który wynosi do kilkunastu dni, często by zaobserwować określone zjawiska konieczne będzie zbadanie długiego odcinka czasu. W takim przypadku wszystkie zjawiska, które istotne są lokalnie, tracą na znaczeniu, kiedy postawione są w szerszym kontekście.</p>
<h2 id="technologia-wykorzystana-do-stworzenia-symulacji">Technologia wykorzystana do stworzenia symulacji</h2>
<h3 id="wymagania-dotyczące-technologii">Wymagania dotyczące technologii</h3>
<p>W rozdziale 2. oraz 3. przedstawiony został problem planowania produkcji i dystrybucji, a także umówiony został sposób, w jaki wymodelowana została symulacja komputerowa. Kolejnym krokiem jest postawienie określonych wymagań dotyczących technologii, jakie wykorzystane mogą być do stworzenia symulacji. Rozpocząć należy od przedstawienia listy funkcjonalności wymaganych od aplikacji:</p>
<ol type="1">
<li><p><strong>Interfejs</strong> – Powinien umożliwiać przeglądanie list MRP w dowolnym momencie (w dowolnym kroku) symulacji. Interfejs użytkownika powinien również zapewniać sterowanie symulacją – tj. umożliwiać jej wstrzymanie, wznowienie, wykonanie pojedynczego kroku, czy n kroków. Ponadto z poziomu GUI<a href="#fn22" class="footnote-ref" id="fnref22"><sup>22</sup></a> istnieć powinna możliwość importowania danych z zewnętrznych (zasilających symulację) z plików CSV, a także eksportu wyników symulacji, by możliwe było obliczenie omawianych w rozdziale 4.1 kluczowych wskaźników efektywności.</p></li>
<li><p><strong>Baza danych</strong> – Ponieważ zarówno ilość danych zasilająca symulację, jak i ilość danych generowanych w wyniku działania symulacji (takich jak zapis stanu magazynowego, czy ruchów towaru między ogniwami łańcucha dostaw w każdym kroku) jest znacząca, należy zadbać o właściwe ich przechowywanie. Niezasadne jest oczywiście trzymanie wszystkich danych wyłącznie w pamięci RAM komputera w czasie działania programu. Oczywiste wydaje się wykorzystanie do tego celu dedykowanej bazy danych. Z tego samego powodu wybrana technologia powinna umożliwiać szybkie i bezproblemowe połącznie z relacyjną bazą danych.</p></li>
<li><p><strong>Operacje IO</strong> – Z uwagi na to, że niezbędne do wykonania symulacji jest zasilenie jej danymi firmy XYZ, a na jej wyjściu oczekiwane jest generowanie plików przeznaczonych do dalszej analizy, wybrana technologia wspierać musi łatwe operacje wejścia i wyjścia na plikach tekstowych (CSV, XML) i binarnych (XLSX). Rozumie się przez to istnienie dedykowanych bibliotek dostępnych na licencji open-source możliwych do wykorzystania przy imporcie, eksporcie, a także przy parsowaniu danych.</p></li>
<li><p><strong>Obiektowość</strong> – Biorąc pod uwagę kompleksowość obiektów w symulacji (rozumianą przez mnogość i różnorodność ich atrybutów) znaczącym ułatwieniem jest mapowania świata rzeczywistego jako obiektów w języku programowania. Nie jest to jednak jedyny argument. Wykorzystanie obiektowego języka programowania umożliwia mapowanie obiektowo-relacyjne (ORM<a href="#fn23" class="footnote-ref" id="fnref23"><sup>23</sup></a>), które przyspiesza integrację aplikacji z serwerem bazy danych.</p></li>
<li><p><strong>Znajomość technologii</strong> – Ostatnim, jednak nie najmniej ważnym, argumentem w dyskusji o technologiach możliwych do wykorzystania jest znajomość danej technologii (danego języka programowania oraz towarzyszących mu frameworków<a href="#fn24" class="footnote-ref" id="fnref24"><sup>24</sup></a>) przez autora niniejszej pracy.</p></li>
</ol>
<h3 id="przegląd-i-wybór-technologii">Przegląd i wybór technologii</h3>
<p>Wymagania określone w rozdziale 3.2.1. nie dają jednoznacznej odpowiedzi na pytanie jaką technologię należy wykorzystać do stworzenia aplikacji. Dodatkowo niektóre z postawionych wymagań, takie jak obiektowość, czy łatwość operacji IO, są wspólne dla wielu wysokopoziomowych, współczesnych języków programowania. Biorąc jednak pod uwagę całokształt sprecyzowanych warunków wstępnych oraz ograniczając przestrzeń rozważań do najbardziej popularnych rozwiązań, wybór można ograniczyć do następujących technologii:</p>
<ul>
<li><p>Java + Swing + JDBC</p></li>
<li><p>C# + WPF + .NET Sql Native Client</p></li>
<li><p>C++ + Qt + ODBC</p></li>
</ul>
<p>Biorąc pod uwagę aspekt czysto techniczny – wszystkie trzy rozwiązania umożliwią stworzenie aplikacji implementującej zarysowany w rozdziale 3.1. model. Różnią się one jednak od siebie znacząco, dlatego należy dokonać nieco głębszej ich analizy, by wybrać rozwiązanie najbardziej odpowiadające omawianemu problemowi.</p>
<p>Największą różnicą pomiędzy Javą a C++ jest sposób zarządzania pamięcią. W C++ to programista odpowiedzialny jest za alokowanie pamięci. Również w jego gestii pozostaje jej zwalnianie - poprzez instrukcję <em>delete</em>. Gdy zaalokowana pamięć pozostaje niezwolniona mimo, że nie jest już wykorzystywana, program (np. przy wywoływaniu określonej funkcji) pochłaniać będzie każdorazowo nowe zasoby, nigdy nie zwalniając tych nieużywanych. Doprowadza to wycieku pamięci - spadku wydajności, a w skrajnych wypadkach zawieszania się innych aplikacji, bądź też całego systemu operacyjnego. Problem ten nie jest obecny w najmniejszym nawet stopniu w Javie. Programista nie tylko nie musi martwić się o to, gdzie przechowywane są aktualnie przetwarzane dane, ale również nie ma obowiązku ich zwalniania. Rozwiązaniem tego problemu jest <em>Garbage Collector</em>. Identyfikuje on zasoby pozostające bez referencji i zwalnia je, umożliwiając alokację nowych danych.</p>
<p>Listing - Przykład wycieku pamięci w kodzie C++</p>
<p>Kolejną istotną różnicą jest sposób wykonywania samego kodu. C++ kompilowany jest do kodu binarnego, który uruchamiany jest bezpośrednio na procesorze. Java zaś kompilowana jest do kodu bajtowego, który uruchamiany jest następnie w JVM<a href="#fn25" class="footnote-ref" id="fnref25"><sup>25</sup></a>. Każde z tych rozwiązań niesie ze sobą określone zyski i straty. Kod wykonywany bezpośrednio na procesorze z reguły przetwarzany będzie znacznie szybciej niż kod wykonywany na emulowanym procesorze Javy. Problem wydajności był dostrzegalny szczególnie w przeszłości, gdy szybkość procesorów i przepustowość pamięci była znacznie niższa niż obecnie. Obecnie nie stanowi to bardzo dużego problemu, jednak w przypadku gdy wymagane jest szybkie wykonywanie złożonych obliczeń i operowanie na dużych zbiorach danych, natywny kod binarny wykonywany będzie znacząco szybciej niż kod Javy.</p>
<p>Rysunek - Budowa JVM</p>
<p>Wykonywanie kodu na maszynie wirtualnej Javy niesie ze sobą jednak inne korzyści. Główną jest kompatybilność kodu bez względu na platformę. Raz napisana aplikacja może być uruchomiona na dowolnej platformie (sprzętowej, aplikacyjnej), jeżeli tylko istnieje odpowiednia dystrybucja JVM. Obecnie zaś trudno znaleźć urządzenie mobilne, czy system, które nie byłby wpierany przez Oracle<a href="#fn26" class="footnote-ref" id="fnref26"><sup>26</sup></a>. Oznacza to łatwość portowania aplikacji, w myśl promowanego przez Sun Microsystems hasła <em>Write once, run anywhere</em>. Nie jest tak niestety w przypadku C++, który skompilowany musi być osobno na każdą platformę uruchomieniową.</p>
<p>Wieloplatformowość Javy oznacza również, że może być ona wykorzystana do tworzenia web serwisów oraz aplikacji webowych. Biorąc zaś pod uwagę obecne trendy rynkowe, nieuchronne wydaje się stopniowe przenoszenie wszystkich dotychczasowych rozwiązań do chmury. Możliwe jest wykorzystanie frameworków takich jak Wt<a href="#fn27" class="footnote-ref" id="fnref27"><sup>27</sup></a>, aby użyć C++ do tworzenia aplikacji webowych, jednak w porównaniu z Javą lub środowiskiem .NET, wsparcie dla serwisów WWW jest tutaj znikome.</p>
<p>Ostatnim argumentem przemawiającym za wykorzystaniem Javy jest brak konieczności stosowania wskaźników, które bardzo często przysparzają programistom wielu problemów. Mogą to być chociażby omówione wcześniej wycieki pamięci.</p>
<p>Pod względem składni języka oraz sposobu zarządzaniem pamięcią C# okazuje się bardzo podobny do Javy. Programista Javy, bez żadnego problemu będzie w stanie zrozumieć kod napisany w C#, jak i odwrotnie. Nie oznacza to jednak, że są to języki bliźniacze, gdyż dzieli je wiele różnic. Pierwszym elementem obecnym w C#, a nieposiadającym żadnego odpowiednika w Javie jest LINQ<a href="#fn28" class="footnote-ref" id="fnref28"><sup>28</sup></a>. W zdecydowany sposób upraszcza on operowanie na kolekcjach – wybieranie obiektów o określonych atrybutach, iterowanie po nich, modyfikowanie obiektów o wybranych cechach itd. Możliwość taka wielokrotnie oznacza oszczędność czasu programisty, a także upraszcza i sprawia bardziej czytelnym sam kod.</p>
<p>Listing - Przykład użycia LINQ w C#</p>
<p>Elementem wspólnym Javy i C# jest jednak to, że nie są one komplilowane do natywnego kodu przetwarzanego przez procesor. O ile jednak Java kompilowana jest do kodu bajtowego uruchamianego na maszynie wirtualnej, C# kompilowany jest do języka pośredniego (MSIL/CIL), który kompilowany jest w czasie rzeczywistym (poprzez kompilator JIT) do natywnych instrukcji procesora. Wadą tego rozwiązania w przeszłości była możliwość tworzenia aplikacji wyłącznie na platformę Windows. Obecnie jednak nie stanowi to żadnego problemu. Wraz z przedstawieniem .NET Core oraz Visual Studio Code możliwe jest wykorzystanie C# do tworzenia aplikacji zarówno na platformę Windows, jak i Linux oraz Mac. Zmiana ta wymusiła rzecz jasna otworzenie środowiska .NET Core, które obecnie dostępne jest jako open-source.</p>
<p>Rysunek - Kompilacja oraz wykonywanie kodu C#</p>
<p>Należy zwrócić uwagę również na same środowisko, w którym tworzona jest aplikacja. Visual Studio wraz z platformą .NET umożliwia tworzenie zarówno aplikacji webowych (Web API, MVC, SPA), jak i mobilnych (Xamarin, Ionic, Cordova) jak i desktopowych (WPF). Nie oznacza to może to aż takiej kompatybilności wskroś platform, jak w przypadku Javy, jednak ciągle pokrycie potencjalnych platform wymaga odnotowania.</p>
<p>Kolejną zaletą C# w stosunku do Javy jest obecność NuGet Package Managera. Ułatwia to w znaczącym stopniu zapytania do web serwisów, tworzenie aplikacji MVC, czy nawet wykorzystywanie technologii takich jak TTS<a href="#fn29" class="footnote-ref" id="fnref29"><sup>29</sup></a>. W przypadku Javy w większości wypadków oznacza to konieczność wykorzystywania bibliotek i frameworków podmiotów trzecich. Do tego nieodzowne okazuje się używanie narzędzi do budowania/zarządzania referencjami takich jak Maven. W przypadku C# jest to zwyczajnie prostsze.</p>
<p>Ostatnią istotną różnicą pomiędzy trzema technologiami jest dziedziczenie klas oraz interfejsów. Zarówno w C++ jak i w C# możliwe jest wielokrotnie dziedziczenie klas, co znacząco ułatwia pracę, gdy programista pragnie wykorzystać wszystkie zalety obiektowego paradygmatu programowania. Zamiast tego Java oferuje wielokrotne dziedziczenie interfejsów, a także wewnętrze klasy anonimowe. Działają one w sposób bardzo podobny do domknięć w Scali. Od wersji Java 8 możliwe jest także wykorzystywanie wyrażeń lambda.</p>
<p>Podsumowując tę analizę należy dojść do wniosku, że równie prostymi i oferującymi potencjalnie najwięcej możliwości technologiami są równorzędnie Java oraz C#, pozostawiając C++ nieco w tyle jako język starszy i natywnie nie wspierający tak wielu rozszerzeń. Należy przy tym zaznaczyć, że ani Java ani C# nie posiada cech, które jednoznacznie wskazywałyby na wyższość jednego rozwiązania nad drugim w świetle naszkicowanych w rozdziale 3.1. wymagań. Do stworzenia symulacji została jednak wykorzystana Java z uwagi na to, że autor niniejszej pracy bardziej doświadczony jest w pracy z Javą i Swingiem, aniżeli w programowaniu w C# z wykorzystaniem Windows Presentation Foundation.</p>
<h2 id="architektura-aplikacji">Architektura aplikacji</h2>
<p>Symulacja podzielona została na dwa zasadnicze elementy: aplikację oraz serwer bazodanowy. Dodatkowo pomiędzy aplikacją a serwerem MS SQL występuje dodatkowa warstwa w postaci interfejsu dostępu do danych.</p>
<p>Warstwa aplikacji odpowiedzialna jest za wszelkie manipulacje danych – począwszy od ich importowania, poprzez parsowanie, skończywszy na zasilaniu nimi bazy danych. Również tutaj przeprowadzana jest sama kalkulacja MRP, stąd też (za pośrednictwem mechanizmu do interpretacji scenariuszy testowych) sterowany jest przebieg samej symulacji. W samej aplikacji nie są jednak przechowywane żadne dane poza tymi, które są aktualnie przetwarzane. Wynik każdej przeprowadzonej symulacji (który stanowi zbiór elementów MRP) przechowywany jest w bazie danych.</p>
<p>Istotnym elementem jest wyodrębniony interfejs wymiany danych pomiędzy aplikacją a serwerem danych. Z uwagi na wygodę oraz czytelność kodu sama aplikacja operuje na generycznych obiektach takich jak <em>Shipment</em> czy <em>Delivery.</em> Przechowywane są one jako rekordy w bazie danych. By jednak możliwe było swobodne operowanie nimi na potrzeby kalkulacji/symulacji, a także z uwagi na potencjalnie wysoką powtarzalność kodu związanego z komunikacją z serwerem, wszelkie operacje bazodanowe zostały wydzielone. Interfejs danych (w oparciu o sterownik JDBC) odpowiedzialny jest za tłumaczenie danych na drodze z/do bazy, a także zbiorcze dokonywanie operacji bazodanowych. Jest to pośrednia realizacja mapowania obiektowo-relacyjnego, ale w uproszczonej formie, odpowiedniej dla tworzonej symulacji.</p>
<p>Rysunek - Architektura aplikacji</p>
<p>Ostatnią warstwą architektury jest serwer bazy danych. Wykorzystany został Microsoft SQL Server w wersji 2017. Częścią aplikacji są skrypty budujące niezbędne tabele oraz zasilające bazę danych danymi niezbędnymi do uruchomienia aplikacji. Każda z tabel posiada unikalny klucz główny lub kompozytowy klucz główny złożony z unikalnej kombinacji dwóch kolumn. Tabele w bazie danych podzielić można na trzy grupy:</p>
<ol type="1">
<li><p>Dane związane bezpośrednio z kalkulacją i przechowujące aktualny stan elementów MRP per ogniwo</p></li>
<li><p><em>Master data</em>, dane stałe analizowanej sieci dystrybucyjnej</p></li>
<li><p>Dane statystyczne generowane po każdej kalkulacji MRP, służące do analizy przebiegu symulacji - obliczania KPI czy tworzenia graficznych reprezentacji procesów takich jak zmiana zapasu magazynowego w czasie</p></li>
</ol>
<blockquote>
<p>Diagram encji użytej bazy danych znajduje się w załączniku 7.4.</p>
</blockquote>
<h1 id="symulacja-komputerowa">Symulacja komputerowa</h1>
<h2 id="kluczowe-wskaźniki-efektywności">Kluczowe wskaźniki efektywności</h2>
<h3 id="motywacja-wyboru-określonych-wskaźników">Motywacja wyboru określonych wskaźników</h3>
<p>TODO: Czemu każdy z nich jest istotny i jakie jest ich przełożenie na biznes</p>
<h3 id="definicje">Definicje</h3>
<p>OFR (Order Fill Rate)</p>
<p><br /><span class="math display">$$OFR = \frac{O_{F}}{O_{T}}*100\% = \frac{O_{F}}{O_{F} + O_{\text{NF}}}*100\%\ \lbrack\%\rbrack$$</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline"><em>O</em><sub><em>F</em></sub></span> – zamówienia zrealizowane <span class="math inline">[<em>S</em><em>U</em>]</span></p>
<p><span class="math inline"><em>O</em><sub><em>T</em></sub></span> – wszystkie zamówienia <span class="math inline">[<em>S</em><em>U</em>]</span></p>
<p><span class="math inline"><em>O</em><sub>NF</sub></span> – zamówienia niezrealizowane <span class="math inline">[<em>S</em><em>U</em>]</span></p>
<p>INV (Inventory Level)</p>
<p><br /><span class="math display"><em>I</em><em>N</em><em>V</em> = <em>I</em><em>N</em><em>V</em><sub><em>A</em></sub> + IN<em>V</em><sub><em>B</em></sub> + … + <em>I</em><em>N</em><em>V</em><sub><em>n</em></sub> [<em>S</em><em>U</em>]</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline">IN<em>V</em><sub><em>n</em></sub></span> – zapas w ogniwie <span class="math inline"><em>n</em></span></p>
<p>DFC (Days Forward Coverage)</p>
<p><br /><span class="math display">$$DFC = \frac{\text{IN}V_{P}}{\text{SA}L_{P}}\ \lbrack dni\rbrack$$</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline">IN<em>V</em><sub><em>P</em></sub></span> – aktualny zapas produktu <span class="math inline"><em>P</em></span></p>
<p><span class="math inline">SA<em>L</em><sub><em>P</em></sub></span> – sprzedaż produktu <span class="math inline"><em>P</em></span> przez ostatnie 30 dni</p>
<p>IST (Inventory Stock Turn)</p>
<p><br /><span class="math display">$$IST = \frac{\text{SA}L_{P,T}}{AVG(INV_{P,T})}\ \lbrack 1\rbrack$$</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline">SA<em>L</em><sub><em>P</em>, <em>T</em></sub></span> – sprzedaż produktu <span class="math inline"><em>P</em></span> w czasie <span class="math inline"><em>T</em></span></p>
<p><span class="math inline"><em>A</em><em>V</em><em>G</em>(<em>I</em><em>N</em><em>V</em><sub><em>P</em>, <em>T</em></sub>)</span> – średni zapas produktu <span class="math inline"><em>P</em></span> w czasie <span class="math inline"><em>T</em></span></p>
<p>DS (Dead Stock)</p>
<p><br /><span class="math display"><strong>D</strong><strong>S</strong> <strong>=</strong> <strong>M</strong><strong>I</strong><strong>N</strong>(<strong>S</strong><sub><strong>P</strong><strong>,</strong> <strong>T</strong></sub>) <strong>[</strong><strong>S</strong><strong>U</strong><strong>]</strong></span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline"><em>M</em><em>I</em><em>N</em>(<em>S</em><sub><em>T</em></sub>)</span> – minimalny zapas produktu <span class="math inline"><em>P</em></span> w czasie <span class="math inline"><em>T</em></span></p>
<p>NPI (Non-performing Inventory)</p>
<p><br /><span class="math display">$$\mathbf{NPI =}\frac{\mathbf{\text{DS}}}{\mathbf{AVG(IN}\mathbf{V}_{\mathbf{T}}\mathbf{)}}\mathbf{*100\%\ \lbrack\%\rbrack}$$</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline">DS</span> – zapas towaru nierotującego</p>
<p><span class="math inline"><em>A</em><em>V</em><em>G</em>(<em>I</em><em>N</em><em>V</em><sub><em>T</em></sub>)</span> – średni zapas towaru w czasie <span class="math inline"><em>T</em></span></p>
<h2 id="scenariusze-testowe">Scenariusze testowe</h2>
<h3 id="zaburzenia-występujące-w-rzeczywistych-łańcuchach-dostaw">Zaburzenia występujące w rzeczywistych łańcuchach dostaw</h3>
<h3 id="scenariusz-1---błędna-prognoza-sprzedaży">Scenariusz 1 - Błędna prognoza sprzedaży</h3>
<ul>
<li><p>Metody tworzenia prognoz sprzedaży (SMO, PSC)</p></li>
<li><p>Badanie wrażliwości łańcucha dostaw – jak zwiększenie się niedokładności prognozy sprzedaży wpływać będzie na wszystkie KPI w łańcuchu dostaw</p></li>
</ul>
<h3 id="scenariusz-2-zmiana-progu-replenishmentu-zmiana-strategii-replenishmentu">Scenariusz 2 – Zmiana progu replenishmentu, zmiana strategii replenishmentu</h3>
<ul>
<li><p>Dokładne omówienie SS/ST, algorytmy wyznaczania SS, kontrolowanie safety</p></li>
</ul>
<ul>
<li><p>Debata na temat tego, jakich parametrów bezpieczeństwa należy używać</p></li>
<li><p>Badanie wrażliwości łańcucha dostaw – jak zmniejszenie/zwiększenie parametrów bezpieczeństwa wpływać będzie na wszystkie KPI w łańcuchu dostaw</p></li>
<li><p>Czy zwiększenie poziomu serwisu o k procent warte jest zwiększenie średniego stanu o l procent?</p></li>
</ul>
<h3 id="scenariusz-3-zmiana-minimalnego-wolumenu-produkcji">Scenariusz 3 – Zmiana minimalnego wolumenu produkcji</h3>
<ul>
<li><p>Z czego wynikać mogą ograniczenia minimalnego wolumenu produkcji</p></li>
<li><p>Debata na temat tego, czy zmiana częstości replenishmentów ma wpływ na średni zapas i poziom obsługi klientów</p></li>
</ul>
<h3 id="scenariusz-4-fałszywe-dane-w-systemach-planowania">Scenariusz 4 – Fałszywe dane w systemach planowania</h3>
<ul>
<li><p>Źródła pochodzenia i potencjalne formy fałszywych danych (PDE)</p></li>
<li><p>Badanie jak wiele czasu musi upłynąć od momentu wystąpienia zaburzenia do powrotu łańcucha dostaw do stanu wejściowego</p></li>
</ul>
<h2 id="dane-testowe">Dane testowe</h2>
<h3 id="informacje-o-danych-testowych">Informacje o danych testowych</h3>
<h3 id="pochodzenie-danych-testowych">Pochodzenie danych testowych</h3>
<h1 id="wyniki">Wyniki</h1>
<h2 id="omówienie-uzyskanych-rezultatów">Omówienie uzyskanych rezultatów</h2>
<h3 id="scenariusz-1">Scenariusz 1</h3>
<h3 id="scenariusz-2">Scenariusz 2</h3>
<h3 id="scenariusz-3">Scenariusz 3</h3>
<h3 id="scenariusz-4">Scenariusz 4</h3>
<h1 id="podsumowanie">Podsumowanie</h1>
<h2 id="weryfikacja-postawionych-tez">Weryfikacja postawionych tez</h2>
<h2 id="wnioski">Wnioski</h2>
<h1 id="załączniki">Załączniki</h1>
<h2 id="standard-tworzenia-sygnatur-elementów-mrp-w-symulacji">Standard tworzenia sygnatur elementów MRP w symulacji</h2>
<p>Z praktycznego punktu widzenia dobrą praktyką jest tworzenie sygnatur generowanych MRP elementów. Umożliwia to łatwe tworzenie referencji pomiędzy poszczególnymi elementami. Jest to istotne również z punktu widzenia użytkownika systemu ERP, który widząc sygnaturę o określonej budowie (zaczynającą się od odpowiedniego znaku alfanumerycznego, posiadającą ściśle określoną długość) jest w stanie zidentyfikować związany z nią MRP element. Odnosząc się również do architektury aplikacji, która oparta może być na relacyjnej bazie danych, jest to rozwiązania pożądane.</p>
<p>O ile implementacja sygnatur nie jest niezbędna w bieżącym zakresie pracy, może być bardzo pomocna w przypadku rozszerzenia ram symulacji.</p>
<p>Tabela - Spis sygnatur elementów MRP</p>
<table>
<thead>
<tr class="header">
<th><strong>MRP Element</strong></th>
<th><strong>Pełna nazwa</strong></th>
<th><strong>Standard sygnatury</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>Stock</td>
<td>Stock</td>
<td>Numeracja nie jest wymagana</td>
</tr>
<tr class="even">
<td>ShipNt</td>
<td>Shipment Notification</td>
<td>2xxxxxx : 7 cyfr, pierwsza to zawsze ‘2’, unikalna</td>
</tr>
<tr class="odd">
<td>PchOrd</td>
<td>Purchase Order</td>
<td>4xxxxxx : 7 cyfr, pierwsza to zawsze ‘4’, unikalna</td>
</tr>
<tr class="even">
<td>PrcOrd</td>
<td>Process Order</td>
<td>6xxxxxx : 7 cyfr, pierwsza to zawsze ‘6’, unikalna</td>
</tr>
<tr class="odd">
<td>PlOrd</td>
<td>Planned Order</td>
<td>8xxxxxx : 7 cyfr, pierwsza to zawsze ‘8’, unikalna</td>
</tr>
<tr class="even">
<td>QMLot</td>
<td>Quality Inspection Lot</td>
<td>0xxxxxx : 7 cyfr, pierwsza to zawsze ‘0’, unikalna</td>
</tr>
<tr class="odd">
<td>Safety Stock</td>
<td>Safety Stock</td>
<td>Numeracja nie jest wymagana</td>
</tr>
<tr class="even">
<td>Order</td>
<td>Order</td>
<td>1xxxxxx : 7 cyfr, pierwsza to zawsze ‘1’, unikalna</td>
</tr>
<tr class="odd">
<td>Deliv</td>
<td>Delivery</td>
<td>3xxxxxx : 7 cyfr, pierwsza to zawsze ‘3’, unikalna</td>
</tr>
<tr class="even">
<td>DepReq</td>
<td>Dependent Requirement</td>
<td>5xxxxxx : 7 cyfr, pierwsza to zawsze ‘5’, unikalna</td>
</tr>
<tr class="odd">
<td>PlORel</td>
<td>Planned Order Release</td>
<td>7xxxxxx : 7 cyfr, pierwsza to zawsze ‘7’, unikalna</td>
</tr>
<tr class="even">
<td>IndReq</td>
<td>Independent Requirement</td>
<td>9xxxxxx : 7 cyfr, pierwsza to zawsze ‘9’, unikalna</td>
</tr>
</tbody>
</table>
<h2 id="standard-tworzenia-znaczników-czasu-elementów-mrp-w-symulacji">Standard tworzenia znaczników czasu elementów MRP w symulacji</h2>
<p>Tabela - Spis znaczników czasu elementów MRP</p>
<table>
<thead>
<tr class="header">
<th><strong>MRP Element</strong></th>
<th><strong>Pełna nazwa</strong></th>
<th><strong>Standard znacznika czasu</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>Stock</td>
<td>Stock</td>
<td>TODO</td>
</tr>
<tr class="even">
<td>ShipNt</td>
<td>Shipment Notification</td>
<td>TODO</td>
</tr>
<tr class="odd">
<td>PchOrd</td>
<td>Purchase Order</td>
<td>TODO</td>
</tr>
<tr class="even">
<td>PrcOrd</td>
<td>Process Order</td>
<td>TODO</td>
</tr>
<tr class="odd">
<td>PlOrd</td>
<td>Planned Order</td>
<td>TODO</td>
</tr>
<tr class="even">
<td>QMLot</td>
<td>Quality Inspection Lot</td>
<td>TODO</td>
</tr>
<tr class="odd">
<td>Safety Stock</td>
<td>Safety Stock</td>
<td>TODO</td>
</tr>
<tr class="even">
<td>Order</td>
<td>Order</td>
<td>TODO</td>
</tr>
<tr class="odd">
<td>Deliv</td>
<td>Delivery</td>
<td>TODO</td>
</tr>
<tr class="even">
<td>DepReq</td>
<td>Dependent Requirement</td>
<td>TODO</td>
</tr>
<tr class="odd">
<td>PlORel</td>
<td>Planned Order Release</td>
<td>TODO</td>
</tr>
<tr class="even">
<td>IndReq</td>
<td>Independent Requirement</td>
<td>TODO</td>
</tr>
</tbody>
</table>
<h2 id="standard-tworzenia-plików-xml-ze-scenariuszami-testowymi">Standard tworzenia plików XML ze scenariuszami testowymi</h2>
<p>TODO: Opracować standard plików XML ze scenariuszami testowymi</p>
<h2 id="tabele-w-bazie-danych">Tabele w bazie danych</h2>
<p><img src="media/image12.png" style="width:6.3in;height:5.01528in" /></p>
<p><img src="media/image13.png" style="width:3.26042in;height:3.05517in" /></p>
<p>TODO: Uzupełnić bibliografię o przeczytane I przejrzane pozycje</p>
<section class="footnotes">
<hr />
<ol>
<li id="fn1"><p>KPI – Key Performance Indicators, wskaźniki wykorzystywane do pomiaru stopnia realizacji celów biznesowych<a href="#fnref1" class="footnote-back">↩</a></p></li>
<li id="fn2"><p>FMCG – Fast Moving Consumer Goods, zróżnicowane dobra sprzedawane często, o względnie niewielkiej wartości i wysokich wolumenach sprzedaży, tj. artykuły higieniczne czy środki czystości<a href="#fnref2" class="footnote-back">↩</a></p></li>
<li id="fn3"><p>MAS – Multi Agent System, system złożony z komunikujących i współpracujących między sobą agentów, realizujących określone cele, częstokroć wykorzystywany w do modelowania rozproszonych i złożonych obliczeniowo problemów<a href="#fnref3" class="footnote-back">↩</a></p></li>
<li id="fn4"><p>KQML - Knowledge Query and Manipulation Language, język i protokół komunikacji pomiędzy agentami i systemami opartymi o dane, umożliwiający wzajemną komunikację<a href="#fnref4" class="footnote-back">↩</a></p></li>
<li id="fn5"><p>SCM – Supply Chain Management, kontrola przepływu materiałów i ładunków między ogniwami łańcucha dostaw, najczęściej realizowana przy pomocy dedykowanego oprogramowania<a href="#fnref5" class="footnote-back">↩</a></p></li>
<li id="fn6"><p>MRP – Material Requrements Planning – ogół procesów planowania zapotrzebowania materiałowego, sterowania produkcją i zapasami<a href="#fnref6" class="footnote-back">↩</a></p></li>
<li id="fn7"><p>SAP – zintegrowane, modułowe oprogramowanie służące do zarządzania zasobami przedsiębiorstwa, stworzone przez SAP SE<a href="#fnref7" class="footnote-back">↩</a></p></li>
<li id="fn8"><p>Replenishment – uzupełnienie zapasów, dostawa<a href="#fnref8" class="footnote-back">↩</a></p></li>
<li id="fn9"><p>RCCP – Rough Cut Capacity Planning – weryfikacja dostępnych zasobów względem planu idealnego, podstawowy element planowania długoterminowego<a href="#fnref9" class="footnote-back">↩</a></p></li>
<li id="fn10"><p>BOM – Bill Of Materials, struktura produktu określająca zestawienie półproduktów, komponentów i surowców wraz z ilościami, które niezbędne są do wytworzenia jednostki produktu końcowego<a href="#fnref10" class="footnote-back">↩</a></p></li>
<li id="fn11"><p>ERP – Enterprise Resource Planning, zbiór systemów informatycznych wspomagających całościowe zarządzanie przedsiębiorstwem oraz jego zasobami<a href="#fnref11" class="footnote-back">↩</a></p></li>
<li id="fn12"><p>APS – Advanced Planning and Scheduling – rozszerzenie standardowych procesów zintegrowanego planowania produkcji oparte o określone rozwiązanie IT, np. OMP Plus<a href="#fnref12" class="footnote-back">↩</a></p></li>
<li id="fn13"><p>APO – Advanced Planner and Optimizer, rozszerzenie SAP R/3 wykorzystywane do planowania dystrybucji w rozproszonym łańcuchu dostaw<a href="#fnref13" class="footnote-back">↩</a></p></li>
<li id="fn14"><p>PIPO – Phase In Phase Out, para produktów, które rozróżnialne są z punktu widzenia planowania (np. posiadają różne BOM, różne metody paletyzacji), lecz nierozróżnialne są dla klienta (posiadają taki sam kod EAN)<a href="#fnref14" class="footnote-back">↩</a></p></li>
<li id="fn15"><p>GI-GO – garbage in – garbage out, niskiej jakości dane wejściowe generować będą niskiej jakości dane wyjściowe, nawet jeżeli przetwarzane są przez najlepiej zaprojektowane i dokładne algorytmy<a href="#fnref15" class="footnote-back">↩</a></p></li>
<li id="fn16"><p>customer facing DC – centrum dystrybucyjne, za pośrednictwem którego realizowane są zamówienia klienta<a href="#fnref16" class="footnote-back">↩</a></p></li>
<li id="fn17"><p>VAS – value added services, manipulacje towarem, usługi które służą dodaniu lub zmianie wartości towaru jak np. umieszczenie naklejek, dodanie darmowej próbki innego produktu, stworzenie paczki wielu produktów oferowanej jako jedno dobro klientowi itd.<a href="#fnref17" class="footnote-back">↩</a></p></li>
<li id="fn18"><p>EAN – ang. European Article Number, numeryczny modularny kod towaru z zapisaną sumą kontrolną służący do identyfikacji towaru, wykorzystywany najczęściej w logistyce magazynowej i sprzedaży detalicznej<a href="#fnref18" class="footnote-back">↩</a></p></li>
<li id="fn19"><p>TMS – Transport Management System, elektroniczny system zarządzania transportem, którego główną kompetencją jest śledzenie statusu oraz kontrolowanie pojazdów znajdujących się w tranzycie<a href="#fnref19" class="footnote-back">↩</a></p></li>
<li id="fn20"><p>YMS – Yard Management System, elektroniczny system zarządzania pojazdami na terenie zakładu przemysłowego, główną kompetencją jest koordynacja dostępu do doków, parkingów i wag<a href="#fnref20" class="footnote-back">↩</a></p></li>
<li id="fn21"><p>MAPE – Mean Absolute Percentage Error, średni błąd odchylenia standardowego<a href="#fnref21" class="footnote-back">↩</a></p></li>
<li id="fn22"><p>GUI – Graphical User Interface, graficzny interfejs użytkownika, powłoka graficzna pozwalająca sterować aplikacją<a href="#fnref22" class="footnote-back">↩</a></p></li>
<li id="fn23"><p>ORM – Object-Relational Mapping, sposób odwzorowania obiektowej architektury systemu informatycznego na bazę danych (lub inny element systemu) o relacyjnym charakterze<a href="#fnref23" class="footnote-back">↩</a></p></li>
<li id="fn24"><p>framework – ramy służące do budowy aplikacji, dostarczają szkielet aplikacji oraz definiują mechanizm jej funkcjonowania, zawierają zestaw generycznych komponentów oraz bibliotek ułatwiających implementację określonych działań<a href="#fnref24" class="footnote-back">↩</a></p></li>
<li id="fn25"><p>JVM – Java Virtual Machine, maszyna wirtualna Javy wyposażona w środowisko uruchomieniowe kodu bajtowego Javy, rozróżniane są dwie główne dystrybucje: JRE (Java Runtime Environment) będąca jedynie środowiskiem uruchomieniowym oraz JDK (Java Development Kit) zawierająca narzędzia dla programistów<a href="#fnref25" class="footnote-back">↩</a></p></li>
<li id="fn26"><p>Oracle – amerykańskie przedsiębiorstwo, które przejęło Sun Microsystems odpowiedzialne za stworzenie języka Java<a href="#fnref26" class="footnote-back">↩</a></p></li>
<li id="fn27"><p>Wt, Witty, framework C++ umożliwiający tworzenie web serwisów, posiada zintegrowany serwer aplikacji, http://www.webtoolkit.eu/<a href="#fnref27" class="footnote-back">↩</a></p></li>
<li id="fn28"><p>LINQ – Language Integrated Query – technologia .NET umożliwiająca zadawanie zapytań na obiektach, przeglądanie kolekcji w prosty sposób przypominający język zapytań SQL<a href="#fnref28" class="footnote-back">↩</a></p></li>
<li id="fn29"><p>TTS – ang. Text To Speech, synteza mowy, przetwarzanie teksu na mowę<a href="#fnref29" class="footnote-back">↩</a></p></li>
</ol>
</section>
</body>
</html>

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
<p><strong>Wydział Informatyki</strong></p>
<p><strong>Katedra Inżynierii Oprogramowania</strong></p>
<p>Inżynieria oprogramowania, procesów biznesowych i baz danych</p>
<p><strong>Krzysztof Zabawa</strong></p>
<p>Nr albumu 13931</p>
<p><strong>Symulacja funkcjonowania łańcucha dostaw.<br />
Wpływ parametrów jego ogniw oraz planowania produkcji i dystrybucji na kluczowe wskaźniki efektywności</strong></p>
<blockquote>
<p>Praca magisterska</p>
<p>dr Krzysztof Barteczko</p>
</blockquote>
<p>Warszawa, luty 2018</p>
<p><strong>Streszczenie</strong></p>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris non augue ac libero viverra rhoncus. Integer ultrices, lacus at tristique hendrerit, felis lacus eleifend tellus, in ultricies mauris quam in nisl. Vestibulum vel dui porta, cursus mauris eu, efficitur libero. Vivamus porta, justo sed fermentum euismod, sem odio lobortis nibh, et eleifend erat metus eu libero. Maecenas efficitur risus vitae erat dictum, sit amet mollis diam pretium. Etiam tincidunt arcu nec arcu commodo, ut rutrum tellus tristique. Pellentesque interdum leo a dignissim maximus. Vestibulum eleifend urna vel dui auctor pulvinar a nec elit. Vivamus a libero lorem. In <strong>vitae</strong> condimentum sem, vitae varius lectus. Curabitur orci massa, placerat vitae arcu vel, auctor posuere purus. Nulla eros turpis, fermentum ac volutpat nec, aliquet in arcu. Duis vulputate nisi luctus condimentum fringilla. Fusce ac dapibus leo. Maecenas a sapien iaculis, convallis nibh sit amet, aliquet massa. Aliquam blandit tortor non nullam.</p>
<p><strong>Słowa kluczowe</strong></p>
<p>Łańcuch dostaw, zarządzanie łańcuchem dostaw, planowanie produkcji, planowanie dystrybucji, MRP</p>
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
<p>Rysunek 1- Model MRP, Źródło: Opracowanie własne</p>
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
<p>Tabela 1 - Wykaz dodatnich elementów MRP</p>
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
<p>Tabela 2 - Wykaz ujemnych elementów MRP</p>
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
<p><img src="media/image3.png" style="width:5.45397in;height:4.8125in" /></p>
<p>Rysunek 2 - MRP Lista produktu 1</p>
<p>Jest to wyjątkowo prosty przypadek. Obecny zapas produktu wynosi 147 [CS]. Każdego dnia spodziewana jest sprzedaż na poziomie 6-9 [CS] reprezentowana przez elementy Independent Requirement. Obecnie żaden z klientów nie zdecydował się na założenie zamówienia – nie są spodziewane są więc żadne wysyłki – stąd brak elementów typu Order czy Delivery. Nie są spodziewane również żadne zasilenia ogniw niższego rzędu, co sugeruje brak elementów PlORel. Możliwe do zauważenia jest natomiast zasilenie z lokacji 5053. Co istotne - występuje przed momentem, gdy projekcja stanu magazynowego osiągnie poziom 0. Można więc określić próg repelnishmentu dla tego produktu. Wynosi on około 50 [CS]. Zwrócić uwagę należy również na wielkość samego zasilenia, która wynosi 100 [CS]. Nie jest to oczywiście wartość przypadkowa. W logice SAP dla każdego produktu określana jest rozdzielczość replenishmentu – minimalna ilość produktu, która może być dostarczona z ogniwa wyższego rzędu. W tym przypadku wynosi ona 100 [CS]. Nie wynika ona z żadnej kalkulacji, a jest odzwierciedleniem biznesowych realiów. W tym konkretnym przypadku do lokacji A751 możemy dostarczyć minimum jedną paletę, a dla produktu 83725610 oznacza to równowartość 100 kartonów [CS].</p>
<p><img src="media/image4.png" style="width:5.51042in;height:4.41283in" /></p>
<p>Rysunek 3 - MRP Lista produktu 2</p>
<p>Również i w tym przypadku mamy do czynienia ze stosunkowo mało skomplikowaną sytuacją. W pierwszej kolejności uwagę należy zwrócić na obecność elementów Quality Inspection Lot oraz Process Order. Sugerują one od razu, że lokacja 2725 jest w rzeczywistości fabryką. Ponieważ oba te elementy występują jednocześnie (tzn. z tą samą datą) wyciągnąć można wniosek, że zlecona została produkcja 1452 [CS] produktu. W chwili generowania listy wyprodukowano już 396 [CS], ciągle odbywa się produkcja pozostałych 1056 [CS]. Wyprodukowane 396 [CS] nie są jednak możliwe do wykorzystania do wysyłki lub sprzedaży, ponieważ nie została przeprowadzona kontrola jakości produktów – nie są one jeszcze zapasem. 10.08 zaplanowana jest kolejna produkcja. Ponieważ nie została jeszcze ona zlecona, a jedynie wstępnie zaplanowana, reprezentowana jest przez MRP element, którym jest Planned Order.</p>
<p>Omówioną uprzednio produkcję zlecono ponieważ do fabryki spływają zlecenia replenishmentów z ogniwa niższego rzędu – lokacji 2621. Jest to jednocześnie jedyna lokacja, która wymaga zasilenia.</p>
<p>Należy również zwrócić uwagę na projekcje stanu magazynowego. Przed produkcją 10.08 wynosi ona aż -3036 [CS]. Potrzeby zasilenia ogniwa niższego rzędu znacznie przewyższają więc zaplanowaną produkcję. Konieczna jest zatem taka korekta planu produkcyjnego, by zaspokoić wszystkie zapotrzebowania.</p>
<p><img src="media/image5.png" style="width:5.51042in;height:4.65458in" /></p>
<p>Rysunek 4 - MRP lista produktu 3</p>
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
<p><img src="media/image6.png" style="width:5.05208in;height:2.83864in" /></p>
<p><span id="_Ref498852285" class="anchor"><span id="_Ref498852174" class="anchor"></span></span>Rysunek 5 - Struktura badanego łańcucha dostaw</p>
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
<p>Listing 1 - Przykład wycieku pamięci w kodzie C++</p>
<p>Kolejną istotną różnicą jest sposób wykonywania samego kodu. C++ kompilowany jest do kodu binarnego, który uruchamiany jest bezpośrednio na procesorze. Java zaś kompilowana jest do kodu bajtowego, który uruchamiany jest następnie w JVM<a href="#fn25" class="footnote-ref" id="fnref25"><sup>25</sup></a>. Każde z tych rozwiązań niesie ze sobą określone zyski i straty. Kod wykonywany bezpośrednio na procesorze z reguły przetwarzany będzie znacznie szybciej niż kod wykonywany na emulowanym procesorze Javy. Problem wydajności był dostrzegalny szczególnie w przeszłości, gdy szybkość procesorów i przepustowość pamięci była znacznie niższa niż obecnie. Obecnie nie stanowi to bardzo dużego problemu, jednak w przypadku gdy wymagane jest szybkie wykonywanie złożonych obliczeń i operowanie na dużych zbiorach danych, natywny kod binarny wykonywany będzie znacząco szybciej niż kod Javy.</p>
<p>Rysunek 6 - Budowa JVM</p>
<p>Wykonywanie kodu na maszynie wirtualnej Javy niesie ze sobą jednak inne korzyści. Główną jest kompatybilność kodu bez względu na platformę. Raz napisana aplikacja może być uruchomiona na dowolnej platformie (sprzętowej, aplikacyjnej), jeżeli tylko istnieje odpowiednia dystrybucja JVM. Obecnie zaś trudno znaleźć urządzenie mobilne, czy system, które nie byłby wpierany przez Oracle<a href="#fn26" class="footnote-ref" id="fnref26"><sup>26</sup></a>. Oznacza to łatwość portowania aplikacji, w myśl promowanego przez Sun Microsystems hasła <em>Write once, run anywhere</em>. Nie jest tak niestety w przypadku C++, który skompilowany musi być osobno na każdą platformę uruchomieniową.</p>
<p>Wieloplatformowość Javy oznacza również, że może być ona wykorzystana do tworzenia web serwisów oraz aplikacji webowych. Biorąc zaś pod uwagę obecne trendy rynkowe, nieuchronne wydaje się stopniowe przenoszenie wszystkich dotychczasowych rozwiązań do chmury. Możliwe jest wykorzystanie frameworków takich jak Wt<a href="#fn27" class="footnote-ref" id="fnref27"><sup>27</sup></a>, aby użyć C++ do tworzenia aplikacji webowych, jednak w porównaniu z Javą lub środowiskiem .NET, wsparcie dla serwisów WWW jest tutaj znikome.</p>
<p>Ostatnim argumentem przemawiającym za wykorzystaniem Javy jest brak konieczności stosowania wskaźników, które bardzo często przysparzają programistom wielu problemów. Mogą to być chociażby omówione wcześniej wycieki pamięci.</p>
<p>Pod względem składni języka oraz sposobu zarządzaniem pamięcią C# okazuje się bardzo podobny do Javy. Programista Javy, bez żadnego problemu będzie w stanie zrozumieć kod napisany w C#, jak i odwrotnie. Nie oznacza to jednak, że są to języki bliźniacze, gdyż dzieli je wiele różnic. Pierwszym elementem obecnym w C#, a nieposiadającym żadnego odpowiednika w Javie jest LINQ<a href="#fn28" class="footnote-ref" id="fnref28"><sup>28</sup></a>. W zdecydowany sposób upraszcza on operowanie na kolekcjach – wybieranie obiektów o określonych atrybutach, iterowanie po nich, modyfikowanie obiektów o wybranych cechach itd. Możliwość taka wielokrotnie oznacza oszczędność czasu programisty, a także upraszcza i sprawia bardziej czytelnym sam kod.</p>
<p>Listing 2 - Przykład użycia LINQ w C#</p>
<p>Elementem wspólnym Javy i C# jest jednak to, że nie są one komplilowane do natywnego kodu przetwarzanego przez procesor. O ile jednak Java kompilowana jest do kodu bajtowego uruchamianego na maszynie wirtualnej, C# kompilowany jest do języka pośredniego (MSIL/CIL), który kompilowany jest w czasie rzeczywistym (poprzez kompilator JIT) do natywnych instrukcji procesora. Wadą tego rozwiązania w przeszłości była możliwość tworzenia aplikacji wyłącznie na platformę Windows. Obecnie jednak nie stanowi to żadnego problemu. Wraz z przedstawieniem .NET Core oraz Visual Studio Code możliwe jest wykorzystanie C# do tworzenia aplikacji zarówno na platformę Windows, jak i Linux oraz Mac. Zmiana ta wymusiła rzecz jasna otworzenie środowiska .NET Core, które obecnie dostępne jest jako open-source.</p>
<p>Rysunek 7 - Kompilacja oraz wykonywanie kodu C#</p>
<p>Należy zwrócić uwagę również na same środowisko, w którym tworzona jest aplikacja. Visual Studio wraz z platformą .NET umożliwia tworzenie zarówno aplikacji webowych (Web API, MVC, SPA), jak i mobilnych (Xamarin, Ionic, Cordova) jak i desktopowych (WPF). Nie oznacza to może to aż takiej kompatybilności wskroś platform, jak w przypadku Javy, jednak ciągle pokrycie potencjalnych platform wymaga odnotowania.</p>
<p>Kolejną zaletą C# w stosunku do Javy jest obecność NuGet Package Managera. Ułatwia to w znaczącym stopniu zapytania do web serwisów, tworzenie aplikacji MVC, czy nawet wykorzystywanie technologii takich jak TTS<a href="#fn29" class="footnote-ref" id="fnref29"><sup>29</sup></a>. W przypadku Javy w większości wypadków oznacza to konieczność wykorzystywania bibliotek i frameworków podmiotów trzecich. Do tego nieodzowne okazuje się używanie narzędzi do budowania/zarządzania referencjami takich jak Maven. W przypadku C# jest to zwyczajnie prostsze.</p>
<p>Ostatnią istotną różnicą pomiędzy trzema technologiami jest dziedziczenie klas oraz interfejsów. Zarówno w C++ jak i w C# możliwe jest wielokrotnie dziedziczenie klas, co znacząco ułatwia pracę, gdy programista pragnie wykorzystać wszystkie zalety obiektowego paradygmatu programowania. Zamiast tego Java oferuje wielokrotne dziedziczenie interfejsów, a także wewnętrze klasy anonimowe. Działają one w sposób bardzo podobny do domknięć w Scali. Od wersji Java 8 możliwe jest także wykorzystywanie wyrażeń lambda.</p>
<p>Podsumowując tę analizę należy dojść do wniosku, że równie prostymi i oferującymi potencjalnie najwięcej możliwości technologiami są równorzędnie Java oraz C#, pozostawiając C++ nieco w tyle jako język starszy i natywnie nie wspierający tak wielu rozszerzeń. Należy przy tym zaznaczyć, że ani Java ani C# nie posiada cech, które jednoznacznie wskazywałyby na wyższość jednego rozwiązania nad drugim w świetle naszkicowanych w rozdziale 3.1. wymagań. Do stworzenia symulacji została jednak wykorzystana Java z uwagi na to, że autor niniejszej pracy bardziej doświadczony jest w pracy z Javą i Swingiem, aniżeli w programowaniu w C# z wykorzystaniem Windows Presentation Foundation.</p>
<h2 id="architektura-aplikacji">Architektura aplikacji</h2>
<p>Symulacja podzielona została na dwa zasadnicze elementy: aplikację oraz serwer bazodanowy. Dodatkowo pomiędzy aplikacją a serwerem MS SQL występuje dodatkowa warstwa w postaci interfejsu dostępu do danych.</p>
<p>Warstwa aplikacji odpowiedzialna jest za wszelkie manipulacje danych – począwszy od ich importowania, poprzez parsowanie, skończywszy na zasilaniu nimi bazy danych. Również tutaj przeprowadzana jest sama kalkulacja MRP, stąd też (za pośrednictwem mechanizmu do interpretacji scenariuszy testowych) sterowany jest przebieg samej symulacji. W samej aplikacji nie są jednak przechowywane żadne dane poza tymi, które są aktualnie przetwarzane. Wynik każdej przeprowadzonej symulacji (który stanowi zbiór elementów MRP) przechowywany jest w bazie danych.</p>
<p>Istotnym elementem jest wyodrębniony interfejs wymiany danych pomiędzy aplikacją a serwerem danych. Z uwagi na wygodę oraz czytelność kodu sama aplikacja operuje na generycznych obiektach takich jak <em>Shipment</em> czy <em>Delivery.</em> Przechowywane są one jako rekordy w bazie danych. By jednak możliwe było swobodne operowanie nimi na potrzeby kalkulacji/symulacji, a także z uwagi na potencjalnie wysoką powtarzalność kodu związanego z komunikacją z serwerem, wszelkie operacje bazodanowe zostały wydzielone. Interfejs danych (w oparciu o sterownik JDBC) odpowiedzialny jest za tłumaczenie danych na drodze z/do bazy, a także zbiorcze dokonywanie operacji bazodanowych. Jest to pośrednia realizacja mapowania obiektowo-relacyjnego, ale w uproszczonej formie, odpowiedniej dla tworzonej symulacji.</p>
<p>Rysunek 8 - Architektura aplikacji</p>
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
<p>Ostatnim etapem przygotowań do analizy symulacji komputerowej jest wybranie zestawu wskaźników, które posłużą do interpretacji wyników. Muszą one odpowiadać realnym potrzebom biznesu i dawać odpowiedź na to, w jakiej kondycji jest analizowana sieć dostaw. W rozdziale 4.1.2. znalazły się wybrane wskaźniki, do obliczenia których danych dostarczy stworzona symulacja.</p>
<p>Bezsprzecznie najważniejszym z punktu widzenia biznesu wskaźnikiem jest <em>OFR</em> – odpowiada on na pytanie jaki procent zamówień klientów jesteśmy w stanie zaspokoić. Bierze on pod uwagę łączny wolumen zamówień oraz wolumen wysyłek, zakładając przy tym (bardzo powszechną) praktykę realizowania wyłącznie części zamówienia, w przypadku gdy produkt nie jest dostępny w całej wymaganej ilości. Należy jednak zdawać sobie sprawę z tego, że OFR nie jest jednocześnie jedyną miarą, która oddaje to, jak klient jest zadowolony ze współpracy z danym dostawcą.</p>
<p>Innym kluczowym wskaźnikiem jest <em>OTD</em><a href="#fn30" class="footnote-ref" id="fnref30"><sup>30</sup></a>, które określa terminowość dostaw. Jest to miara bardzo istotna, jednak trudna w symulowaniu. Zależy bowiem od aktualnej sytuacji na rynku transportowym (podaży transportu, popytu innych firm na transport), aktualnego obciążenia ogniw w łańcuchu dostaw i spowodowanych tym opóźnień, czy wypadków losowych takich jak uszkodzenie się samochodu. Oczywiście istnieje szereg potencjalnych usprawnień, które pozytywnie mogą wpłynąć na OTD (jak na przykład zacieśnienie relacji z przewoźnikami, czy negocjacje lepszych kontaktów transportowych), jednak w zdecydowanej ilości przypadków nie ma możliwości dokonania systematycznej poprawy OTD poprzez określony sposób zarządzania łańcuchem dostaw. Dlatego właśnie OFR nie będzie analizowany w tej pracy.</p>
<p>Warto zaznaczyć, że analiza OFR/OTD może posłużyć do naszkicowania przybliżonego obrazu SAMBC<a href="#fn31" class="footnote-ref" id="fnref31"><sup>31</sup></a>, który jest finalnym wskaźnikiem wykorzystywanym przez klientów.</p>
<p>Drugim wybranym wskaźnikiem jest <em>INV</em>, średni poziom zapasu w łańcuchu dostaw. Jak można się domyślić – im mniejszy zapas, tym lepiej. Wysoki zapas oznacza przede wszystkim dużą ilość pieniędzy, które zamrożone są w gotowych produktach gotowych, półproduktach i surowcach. Zmniejszenie zapasu umożliwia obracanie większym kapitałem – czynienie inwestycji, czy chociażby bezpieczne lokowanie środków na giełdzie. Należy zdawać sobie sprawę z tego, że większe zapasy oznaczają również konieczność ponoszenia wyższych kosztów z tytuły przechowywania (w przypadku korzystania z usług 3PL<a href="#fn32" class="footnote-ref" id="fnref32"><sup>32</sup></a>), czy budowy bardziej zasobnej infrastruktury własnej (w przypadku braku outsourcingu usług logistycznych).</p>
<p>Nie jest też pewne, że wyprodukowane dobra będzie można sprzedać. Jest to szczególnie dostrzegalne w branży FMCG, gdzie cykl życia produktu mierzony jest nieraz w tygodniach. Bardzo często przedsiębiorstwa FMCG organizują kampanie reklamowe z udziałem gwiazd, czy też emitują specjalne np. świąteczne lub olimpijskie edycje produktów. W przypadku, gdy produkcja zdecydowanie przewyższy popyt, w magazynach znajdą się produkty niebędące w aktualnej sprzedaży. Częstokroć koszt przepakowania produktów przewyższa wartość samych dóbr i bardziej opłacalne jest zutylizowanie produktów, niż ich manipulacja umożliwiająca późniejszą sprzedaż.</p>
<p>Analizując powyższe przesłanki możliwe jest wyciągnięcie wniosku, że konieczne jest dążenie do jak najniższych zapasów. Nie jest to jednak prawdą. Zapas (a przede wszystkim tzw. <em>safety stock</em> ) ma na celu kompensowanie nieregularności dostaw i skończoną dokładność prognoz sprzedaży. Należy więc szukać kompromisu pomiędzy satysfakcjonującym poziomem obsługi klienta a aktualnym stanem zapasów.</p>
<p>Kolejnym wskaźnikiem wybranym do analizy jest <em>DS</em>. Jest to poniekąd pochodna INV. DS pokazuje poniżej jakiego pułapu poziom zapasu nigdy nie spada. O ile sytuacją niepożądaną jest brak możliwości realizowania zamówień klienta wskutek braku towaru, również bardzo wysoki DS. jest indykatorem niesprawnie działającego łańcucha dostaw. Jeżeli bowiem jest on wysoki, oznacza to, że przedsiębiorstwo chronicznie posiada na stanie zbyt wysokie wolumeny produktu w stosunku do jego sprzedaży. Można w takim wypadku typować kilka czynników, będących potencjalnie źródłem problemów:</p>
<ol type="1">
<li><p>Powtarzalne, zbyt wysokie prognozy sprzedaży</p></li>
<li><p>Wysokie minimalne partie produkcyjne</p></li>
<li><p>Zlecanie produkcji w sprzeczności z sugestiami systemów planowania</p></li>
</ol>
<p>DS nie odpowiada na pytanie, gdzie w łańcuchu dostaw leży problem, jest jednak wskaźnikiem, że należy dokonać dokładnej analizy konkretnego przypadku, by zrozumieć prawdziwą przyczynę zbyt wysokiego minimalnego zapasu.</p>
<p>O ile DS jest wskaźnikiem bezwzględnym, istnieje jego wariacja w postaci <em>NPI</em>. Jest to stosunek minimalnego zapasu do średniego zapasu. Zasadniczo jest to względna miara DS.</p>
<p><em>IST</em> jest jednym z podstawowych wskaźników informujących o sprawnością zarządzania zapasami przedsiębiorstwa. Określa on rotację zapasów, czyli to ile razy obrócono zapasami, by otrzymać określony ostateczny przychód. Inna interpretacja tego wskaźnika to liczba razy, którą stara partia towaru została zastąpiona nową w danej jednostce czasu.</p>
<p>Oczywiście wskaźnik IST jest czysto teoretyczny i nie oznacza, że faktycznie (fizycznie) dana zmiana towaru nastąpiła. W bezpośredni sposób decydują o tym systemy WMS<a href="#fn33" class="footnote-ref" id="fnref33"><sup>33</sup></a>, które wymuszają stosowanie określonych kolejek takich jak FIFO<a href="#fn34" class="footnote-ref" id="fnref34"><sup>34</sup></a>, LIFO<a href="#fn35" class="footnote-ref" id="fnref35"><sup>35</sup></a>, czy FEFO<a href="#fn36" class="footnote-ref" id="fnref36"><sup>36</sup></a>, decydując o fizycznej rotacji towaru. Wybór algorytmu do zarządzania wydawaniem zapasów zależy oczywiście od rodzaju towaru i profilu działalności przedsiębiorstwa.</p>
<p>Zasadniczo w firmach z sektora FMCG, a takie rozważane są w tej pracy, im wyższy wskaźnik rotacji tym lepiej – oznacza to bowiem, że średni czas od wyprodukowania produktu do jego wysłania do klienta jest niski, a co za tym idzie – średni zapas nie jest przesadnie wysoki.</p>
<p>Ostatnim z wybranych wskaźników jest <em>DFC</em>. Określa on pokrycie potencjalnego przyszłego popytu na podstawie historycznych danych sprzedaży. Należy używa go jednak z rozwagą. Nie będzie on odpowiadał rzeczywistości, w przypadku gdy planowany jest wzrost sprzedaży wskutek np. promocji na dany artykuł, albo kiedy nadbudowywany jest zapas z powodu serwisowania linii produkcyjnej. Jednak w normalnym okolicznościach również i on z powodzeniem może służyć do oceny zdrowia łańcucha dostaw.</p>
<p>W idealnym przypadku, kiedy wysyłki do klientów odbywają się zgodnie z prognozą sprzedaży i nie istnieją żadne ograniczenia po stronie produkcji/dostaw DFC powinien dążyć do wartości odpowiadającej <em>safety stock</em> bądź <em>safety time</em>, a więc zadanym parametrom bezpieczeństwa. Określenie właściwych nominalnych parametrów bezpieczeństwa stanowi w dziedzinie logistyki osobny problem, o którym traktują inne prace naukowe. W biznesowej rzeczywistości najczęściej obliczane są one przy pomocy bazowych wzorów, które następnie poddawane są obróbce przez osoby zajmujące się planowaniem dystrybucji, które weryfikują je względem doświadczeń pozyskanych empirycznie. Co do zasady jednak, znając responsywność sieci dystrybucyjnej, możemy porównywać DFC z oczekiwanym czasem reakcji na bodziec (zapotrzebowanie).</p>
<h3 id="definicje">Definicje</h3>
<p>OFR (Order Fill Rate)</p>
<p><br /><span class="math display">$$OFR = \frac{O_{F}}{O_{T}}*100\% = \frac{O_{F}}{O_{F} + O_{\text{NF}}}*100\%\ \lbrack\%\rbrack$$</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline"><em>O</em><sub><em>F</em></sub></span> – zamówienia zrealizowane <span class="math inline">[<em>S</em><em>U</em>]</span></p>
<p><span class="math inline"><em>O</em><sub><em>T</em></sub></span> – wszystkie zamówienia <span class="math inline">[<em>S</em><em>U</em>]</span></p>
<p><span class="math inline"><em>O</em><sub>NF</sub></span> – zamówienia niezrealizowane <span class="math inline">[<em>S</em><em>U</em>]</span></p>
<p>INV (Inventory Level)</p>
<p><br /><span class="math display"><em>I</em><em>N</em><em>V</em> = <em>A</em><em>V</em><em>G</em>(<em>I</em><em>N</em><em>V</em><sub><em>A</em></sub> + IN<em>V</em><sub><em>B</em></sub> + … + <em>I</em><em>N</em><em>V</em><sub><em>n</em></sub>) [<em>S</em><em>U</em>]</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline">IN<em>V</em><sub><em>n</em></sub></span> – zapas w ogniwie <span class="math inline"><em>n</em></span></p>
<p>DFC (Days Forward Coverage)</p>
<p><br /><span class="math display">$$DFC = AVG\left( \frac{\text{IN}V_{P}}{AVG(SAL_{P,T})} \right)\ \lbrack dni\rbrack$$</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline">IN<em>V</em><sub><em>P</em></sub></span> – aktualny zapas produktu <span class="math inline"><em>P</em></span></p>
<p><span class="math inline">SA<em>L</em><sub><em>P</em></sub></span> – sprzedaż produktu <span class="math inline"><em>P</em></span> przez ostatnie 30 dni</p>
<p>IST (Inventory Stock Turn)</p>
<p><br /><span class="math display">$$IST = \frac{\text{SA}L_{P,T}}{\text{IN}V_{P,T}}\ \lbrack 1\rbrack$$</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline">SA<em>L</em><sub><em>P</em>, <em>T</em></sub></span> – sprzedaż produktu <span class="math inline"><em>P</em></span> w czasie <span class="math inline"><em>T</em></span></p>
<p><span class="math inline">IN<em>V</em><sub><em>P</em>, <em>T</em></sub></span> – średni zapas produktu <span class="math inline"><em>P</em></span> w czasie <span class="math inline"><em>T</em></span></p>
<p>DS (Dead Stock)</p>
<p><br /><span class="math display"><strong>D</strong><strong>S</strong> <strong>=</strong> <strong>M</strong><strong>I</strong><strong>N</strong>(<strong>S</strong><sub><strong>P</strong><strong>,</strong> <strong>T</strong></sub>) <strong>[</strong><strong>S</strong><strong>U</strong><strong>]</strong></span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline"><em>M</em><em>I</em><em>N</em>(<em>S</em><sub><em>T</em></sub>)</span> – minimalny zapas produktu <span class="math inline"><em>P</em></span> w czasie <span class="math inline"><em>T</em></span></p>
<p>NPI (Non-performing Inventory)</p>
<p><br /><span class="math display">$$\mathbf{NPI =}\frac{\mathbf{\text{DS}}}{\mathbf{AVG(IN}\mathbf{V}_{\mathbf{T}}\mathbf{)}}\mathbf{*100\%\ \lbrack\%\rbrack}$$</span><br /></p>
<p>Gdzie:</p>
<p><span class="math inline">DS</span> – zapas towaru nierotującego</p>
<p><span class="math inline"><em>A</em><em>V</em><em>G</em>(<em>I</em><em>N</em><em>V</em><sub><em>T</em></sub>)</span> – średni zapas towaru w czasie <span class="math inline"><em>T</em></span></p>
<h2 id="narzędzia-wykorzystane-do-analizy-łańcucha-dostaw">Narzędzia wykorzystane do analizy łańcucha dostaw</h2>
<p>Dokonując przeglądu literatury pod kątem kluczowych wskaźników efektywności, możliwe jest znalezienie wielu wielkości, które mają odpowiedzieć na pytanie w jakiej kondycji znajduje się analizowany łańcuch dostaw. Trzeba jednak zwrócić uwagę, że liczby nie zawsze i nie w pełni oddają rzeczywistość. Przeglądając definicje zawarte w rozdziale 4.1.2. można zauważyć, że w wielu przypadkach wskaźniki oparte są o wartości średnie. Oznacza to, że reprezentują one przeciętny trend, pozostawiając bez komentarza sposób w jaki dokonywały się zmiany danej wielkości, czy częstość zmian. Z pomocą przychodzą jednak dwa narzędzia, które umożliwiają zrozumienie pochodzenia wartości badanych wskaźników.</p>
<p>Pierwszym narzędziem są wykresy HIT<a href="#fn37" class="footnote-ref" id="fnref37"><sup>37</sup></a>. Na pierwszy rzut oka wydają się one prymitywne, ponieważ jedyną wartość, którą reprezentują to historyczny zapis stanu magazynowego produktów. Możliwe jest jednak odczytanie z nich znacznie większej ilości informacji, niż mogłoby się to początkowo wydawać. Mogą to być chociażby:</p>
<ol type="1">
<li><p>Częstotliwość dostaw</p></li>
<li><p>Średnia wielkość dostaw</p></li>
<li><p>Okresy, kiedy produkt był niedostępny</p></li>
<li><p>Procent czasu, kiedy produkt znajdował się powyżej/poniżej nominalnej wartości parametrów bezpieczeństwa</p></li>
<li><p>Ilość towaru pozostająca w tranzycie</p></li>
</ol>
<p>Widać więc, że gama zastosowań wykresów HIT jest szeroka i można dokonać z ich pomocą szerokiej analizy i to bez potrzeby stosowania dodatkowych i wyszukanych wskaźników KPI. Warto jednak pamiętać, że stan zapasu jest jedynie efektem określonego stosunku podaży produktów (ich produkcji) oraz popytu na nie (zamówień klientów). Należy więc analizować go w parze z zestawieniem prognoz sprzedaży z wysyłkami.</p>
<p>O ile wykresy HIT umożliwiają zaobserwowanie problemów, które występują głównie po stronie produkcji, dystrybucji, czy transportu, zestawienia prognoz sprzedaży z wysyłkami służyć powinny przede wszystkim do obserwacji i analizy problemów po stronie niespodziewanego popytu, bądź też błędnej prognozy sprzedaży.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 9 - Przykładowy wykres prognozy sprzedaży oraz faktycznych wysyłek z dobową rozdzielczością</p>
<p>Zestawienia te (nazywane również w skrócie <em>shipments vs. forecast</em>) na pozór również nie są zbyt wyszukanym narzędziem – reprezentują one bowiem historyczne dane dotyczące prognozy sprzedaży oraz faktycznie zrealizowanych zamówień. Jednak również i w tym wypadku pogłębiona analiza może odpowiedzieć na pytanie takie jak:</p>
<ol type="1">
<li><p>Czy przeciętne zapotrzebowanie na produkt pokrywa się z przeciętną sprzedażą?</p></li>
<li><p>Czy chwilowa niedostępność produktu związana jest z aktywnością, promocją, trendem sezonowym, który nie został uwzględniony w prognozie sprzedaży?</p></li>
<li><p>Czy wysyłki niezrealizowane w poprzednim tygodniu zostały uwzględnione w prognozie sprzedaży na bieżący tydzień?</p></li>
<li><p>Czy dany produkt w ogóle posiadał prognozę sprzedaży w danym czasie, czy były spodziewane jego wysyłki?</p></li>
<li><p>Czy klient którego udział w wysyłkach jest największy jest faktycznie klientem, którego wzmożonej aktywności się spodziewaliśmy?</p></li>
</ol>
<p>Jak widać – również w tym przypadku pozornie nieskomplikowane narzędzie może dostarczyć odpowiedzi na wiele pytań. Istnieje co prawda cienka granica pomiędzy tym, co powinno być interesujące dla osób analizujących łańcuch dostaw i osób zajmujących się organizacją oraz planowaniem sprzedaży, jednak z punktu widzenia przedsiębiorstwa jako całości konieczne jest znalezienie odpowiedzi na wszystkie te pytania.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 10 - Przykładowy wykres prognozy sprzedaży oraz faktycznych wysyłek z tygodniową rozdzielczością</p>
<p>Możliwe jest więc wyciągnięcie wniosku, jakoby pełne zrozumienie zdrowia łańcucha dostaw możliwe było wyłącznie po przeanalizowaniu zarówno wybranych wskaźników KPI, jak i wykresów HIT, a także wykresów <em>shipments vs. forecast</em>. Dopiero kompilacja danych pochodzących z tych wszystkich źródłem posłużyć może to wyciągnięcia wiążącej i jednoznacznej oceny.</p>
<p>Z tego powodu właśnie przy komentowaniu scenariuszy testowych wykorzystane zostało pełne spektrum dostępnych narzędzi i wskaźników, tak by przedstawiana ocena była możliwie obiektywna i znajdowała poparcie w więcej niż jednym indykatorze.</p>
<h2 id="scenariusze-testowe-oraz-omówienie-wyników">Scenariusze testowe oraz omówienie wyników</h2>
<h3 id="scenariusz-1---błędna-prognoza-sprzedaży">Scenariusz 1 - Błędna prognoza sprzedaży</h3>
<p>Wśród przedsiębiorstw FMCG istnieje powiedzenie, że prognoza sprzedaży jest zawsze błędna. Jest w tym oczywiście wiele racji. Nawet dysponując najobszerniejszą bazą danych historycznych i precyzyjnymi planami dotyczącymi sprzedaży trudno jest stworzyć dokładną i obiektywnie dobrą prognozę. Aby zrozumieć pochodzenie prognozy sprzedaży trzeba cofnąć się na moment do rozdziału 2.1., gdzie przedstawiony został model MRP. Wynika z niego, w dużym uproszczeniu, że końcowa prognoza sprzedaży dla produktu X jest pochodną planu strategicznego rozwoju firmy, planu sprzedaży określonego portfolio, czy nawet określonej rodziny produktów. Wartości te uzupełnione o tzw. <em>business intelligence</em> pochodzący z jednostek zajmujących się utrzymywaniem kontaktów z klientami formują bloki sprzedażowe, które najczęściej posiadają tygodniową rozdzielczość.</p>
<p>Skąd więc bierze się prognoza sprzedaży na poszczególne dni? Choć początkowo trudno w to uwierzyć, nawet globalne przedsiębiorstwa FMCG uciekają się do bardzo prostych sposobów. Tygodniowe bloki spodziewanej sprzedaży dzielone są równo ilość dni, w których spodziewana jest sprzedaż produktów – najczęściej więc 5, 6 lub 7. Tak Przygotowana prognoza sprzedaży trafia z określoną częstotliwością bezpośrednio do systemów planowania, gdzie odbywa się kalkulacja MRP.</p>
<p>Od tego momentu inicjatywa zarządzania prognozą zostaje przeniesiona z człowieka na systemy informatyczne. SAP predefiniuje mechanizm konsumpcji prognozy popytu, która opiera się na trzech kluczowych aspektach:</p>
<ol type="1">
<li><p>Sposobie konsumowania prognozy sprzedaży:</p>
<ol type="a">
<li><p>Wyłącznie wstecz</p></li>
<li><p>Wyłącznie w przód</p></li>
<li><p>Wstecz oraz w przód jednocześnie</p></li>
</ol></li>
<li><p>Priorytecie konsumowania prognozy sprzedaży:</p>
<ol type="a">
<li><p>Najpierw w przód, następnie wstecz</p></li>
<li><p>Najpierw wstecz, następnie w przód</p></li>
</ol></li>
<li><p>Horyzoncie konsumowania prognozy sprzedaży</p>
<ol type="a">
<li><p><em>n</em> dni do przodu</p></li>
<li><p><em>m</em> dni do tyłu</p></li>
</ol></li>
</ol>
<p>Tak więc po wprowadzeniu zamówienia klienta (ręcznym, czy też za pośrednictwem EDI<a href="#fn38" class="footnote-ref" id="fnref38"><sup>38</sup></a>) następuje pomniejszenie pozostającej prognozy sprzedaży o wielkość zamówienia. Wszystko dzieje się zaś w ramach parametrów zdefiniowanych w SAP. Po wygaśnięciu aktualności prognozy sprzedaży następuje całkowite usunięcie poprzedniej prognozy sprzedaży ze wszystkich systemów planowania i zastąpienie jej nową, która została skonstruowana w dokładnie ten sam sposób.</p>
<p>Bardzo często SAP nie pozostaje jedynym systemem, który ingeruje w prognozy sprzedaży. Wiele zewnętrznych firm trzecich oferuje rozwiązania integrujące się z SAP, które na celu mają udoskonalenie prognozy sprzedaży. Są to systemy oparte o przetwarzanie big data<a href="#fn39" class="footnote-ref" id="fnref39"><sup>39</sup></a>. Analizują one trendy towarzyszące poszczególnym produktom, jak np. tendencja do sprzedaży wyższej/niższej niż prognozowana, wysyłki realizowane głównie w określony dzień tygodnia, określony klient stale powiększający wolumeny zamówień itd. Systemy te codziennie wysyłają do SAP zaktualizowaną prognozę sprzedaży, która cechuje się niższym względnym błędem, oraz niższymi parametrami MAPE czy MAD<a href="#fn40" class="footnote-ref" id="fnref40"><sup>40</sup></a>.</p>
<p>Wszystkie omówione w tym rozdziale mechanizmy zaimplementowane zostały w stworzonej symulacji komputerowej. Oczywiste wydaje się, że wysyłki powyżej prognozowanej sprzedaży wiązać mogą się z niedostępnością produktu, a poniżej – nadmiernie dużym zapasem. Autor niniejszej pracy pragnie znaleźć odpowiedź na pytanie – jak duże odchylenie wysyłek od prognozy sprzedaży powoduje zauważalne i odczuwalne skutki w parametrach sieci dostaw. Na początku przeanalizujmy przypadek, w którym w każdym tygodniu wysyłki odpowiadają prognozie sprzedaży.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 11 - Shipments vs. forecast, zamówienia składane zgodnie z prognozą sprzedaży</p>
<p>Mało pouczające i zdecydowanie odstające od rzeczywistości byłoby symulowanie zamówień dokładnie pokrywających się z prognozą sprzedaży. Do symulacji wprowadzone zostały elementy losowości takich jak każdorazowo losowo generowane godziny załadunków i rozładunków, czy opóźnienia dostaw dotykające część samochodów w tranzycie. Biorąc jednak pod uwagę statyczny parametr bezpieczeństwa (próg replenishmentu), który zapewnia pokrycie na około 7 dni wysyłek, zakłócenia te nie byłyby w stanie w istotny sposób oddać biznesowej rzeczywistości. Dlatego właśnie nawet w pierwszym scenariuszu, mimo zakładania wysyłek zgodnie z prognozą sprzedaży, zgodność ta zakładana jest na poziomie tygodnia, nie zaś dnia. Rozkład zamówień został przedstawiony na rysunku 11.</p>
<p>Tabela 3 – Zestawienie wskaźników, zamówienia składane zgodnie z prognozą sprzedaży</p>
<table>
<thead>
<tr class="header">
<th><strong>OFR</strong></th>
<th><strong>INV</strong></th>
<th><strong>DFC</strong></th>
<th><strong>IST</strong></th>
<th><strong>DS</strong></th>
<th><strong>NPI</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>99,33%</td>
<td>1535,17</td>
<td>6,34</td>
<td>15,06</td>
<td>55,00</td>
<td>3,58%</td>
</tr>
</tbody>
</table>
<p>Mimo zamówień, których wielkość wielokrotnie przewyższała prognozowaną dzienną sprzedaż, nie doprowadziło to w żadnym wypadku do zdestabilizowania się sieci dostaw. Parametr bezpieczeństwa doskonale rekompensował niedokładność prognozy sprzedaży, co doprowadziło do uzyskania bardzo wysokiego wskaźnika procentu zrealizowanych zamówień – 99,33%.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 12 – Wykres HIT, zamówienia składane zgodnie z prognozą sprzedaży</p>
<p>Analizując przebieg stanu magazynowego widać co prawda, że ilość towaru przez większość czasu symulacji trwającej 16 tygodni znajdowała się poniżej <em>stanu idealnego</em>, tj. była poniżej progu replenishmentu (1750 jednostek), jednak jest to sytuacja całkowicie normalna biorąc pod uwagę założoną wariację zamówień klientów. Co jest jednak warte odnotowania to, że w zasadzie przez cały czas trwania symulacji nie istniało jakiekolwiek zagrożenie ze strony długiej niedostępności produktów. Nawet kiedy 17.01.2018 (czas w symulacji) zapas wyczerpał się, jego stan wrócił do normalnego poziomu 2 dni później. W rzeczywistym przypadku, posiadając wiedzę o towarze w tranzycie, prawdopodobnie możliwa byłaby negocjacja daty dostawy z klientem i sytuacja ta nie zakończyłaby się niezrealizowaniem zamówienia.</p>
<p>Zaledwie 3,58% całego zapasu stanowił towar, który nie rotuje, co również jest bardzo dobrym rezultatem. Średnia wartość dynamicznego wskaźnika pokrycia oscylowała wokół wartości 6,34 dnia. Biorąc pod uwagę, że próg replenishmentu wynoszący 1750 jednostek zapewniał średnio pokrycie na 8 dni wysyłek, również i ta wartość nie budzi żadnych zastrzeżeń.</p>
<p>Reasumując – przewidywalność wysyłek sprawia, że planowanie produkcji i dystrybucji nie nastręcza żadnych problemów, a łańcuch dostaw łatwo jest kontrolować. W przypadku wysokiej dokładności prognoz sprzedaży łatwo jest również dokonać optymalizacji progu replenishmentu, co skutecznie zmniejsza ilość gotówki, która dla przedsiębiorstwa zamrożona jest w towarze.</p>
<p>Sytuacja wygląda zupełnie inaczej w przypadku, gdy założymy odchylenie standardowe na poziomie 20%. Już tak niewielka przeciętna różnica pomiędzy planowaną sprzedażą, a fizycznymi wysyłkami doprowadza do znaczącej zmiany badanych wskaźników efektywności. Ich zestawienie zawarte zostało w tabeli 4.</p>
<p>Tabela 4 - Zestawienie wskaźników, zamówienia składane poniżej prognozy sprzedaży</p>
<table>
<thead>
<tr class="header">
<th><strong>OFR</strong></th>
<th><strong>INV</strong></th>
<th><strong>DFC</strong></th>
<th><strong>IST</strong></th>
<th><strong>DS</strong></th>
<th><strong>NPI</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>100,00%</td>
<td>2409,15</td>
<td>10,44</td>
<td>9,60</td>
<td>751,00</td>
<td>31,17%</td>
</tr>
</tbody>
</table>
<p>Co nie powinno być zaskoczeniem, to fakt, że w takim wypadku udało się zrealizować 100% zamówień klientów. Wynik, z którego dumne byłoby nie jedno przedsiębiorstwo FMCG. Warto jednak zwrócić uwagę, na to, jakim kosztem jest to obkupione. Aż 31.17% towaru jest towarem, który nie rotuje. Efektem jest spadek wskaźnika rotacji z 15,06 na 9,60. Przekłada się to na 751 jednostek towaru, które w zasadzie pozostają nieustająco nienaruszone w magazynie.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 13 - Wykres HIT, zamówienia składane poniżej prognozy sprzedaży</p>
<p>Śledząc wykres HIT dla przypadku, w którym konsumowane jest mniej produktu, niż zakłada prognoza sprzedaży można od razu zauważyć, że praktycznie przez cały czas trwania symulacji (nie licząc czasu przed pierwszym replenishmentem), zapas pozostawał znacząco wyższy niż próg replenishmentu. Przeciętnie w magazynie znajdowało się 2409 jednostek produktu, podczas kiedy prób replenishmentu wynosił 1750 jednostek. Przekładało się to na przeciętnie 10,44 dnia pokrycia, w stosunku do docelowych 8 dni. Nawet najbardziej agresywne i odbiegające od prognozy sprzedaży (z punktu widzenia pojedynczego dnia) zamówienia nie spowodowały znacznego spadku zapasu.</p>
<p>Sytuacja ta jest bardzo prosta do wytłumaczenia. W przypadku, kiedy nie dysponujemy dodatkowym systemem, który każdego dnia uaktualnia prognozę sprzedaży na podstawie aktualnych trendów (w tym przypadku trendu sprzedaży poniżej prognozy) prognoza sprzedaży pozostaje nieskonsumowana. Ciągle jest jednak ona elementem MRP, który generuje zapotrzebowania na produkcję. Po upłynięciu tygodnia do systemów planowania wpływa kolejna zawyżona prognoza sprzedaży, która również w całości nie zostanie skonsumowana. W przypadku kiedy sytuacja ta powtarza się każdego tygodnia, następuje inflacja zapotrzebowania. Każdorazowo jest ono większe niż rzeczywiste, co przyczynia się do zwiększonego stanu magazynowego. To znajduje zaś oczywiste przełożenie na badane wskaźniki KPI.</p>
<p>Tabela 5 - Zestawienie wskaźników, zamówienia składane powyżej prognozy sprzedaży</p>
<table>
<thead>
<tr class="header">
<th><strong>OFR</strong></th>
<th><strong>INV</strong></th>
<th><strong>DFC</strong></th>
<th><strong>IST</strong></th>
<th><strong>DS</strong></th>
<th><strong>NPI</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>90,69%</td>
<td>1142,37</td>
<td>4,35</td>
<td>20,24</td>
<td>0,00</td>
<td>0,00%</td>
</tr>
</tbody>
</table>
<p>Trzecia przeanalizowana sytuacja (tabela 5), to scenariusz w którym tydzień w tydzień zamówienia przewyższają prognozę sprzedaży, średnio o 20%. Co wymaga największej uwagi, to fakt jak bardzo spadł poziom serwisu – z 99,33% na zaledwie 90,69%. Jest to bardzo niski wynik, który w rzeczywistym przypadku wymagałby natychmiastowej interwencji. Odpowiedzialne są to za trzy okresy niedostępności produktu:</p>
<ul>
<li><p>17.01.2018 – 19.01.2018</p></li>
<li><p>14.02.2018 – 17.02.2018</p></li>
<li><p>01.04.2018 – 02.04.2018</p></li>
</ul>
<p>Łącznie oznacza to, że 9 spośród 112 stanowiły dni, kiedy magazyn nie był w stanie zrealizować żadnych wysyłek. Niesie to ze sobą wiele konsekwencji. Pomijając bezpośredni sposób w jaki przekłada się to wskaźniki, destabilizuje to łańcuch dostaw również na innych płaszczyznach.</p>
<p>Kiedy okres niedostępności produktu jest znaczący, zazwyczaj spodziewać się można klientów powtórnie składających te same zamówienia. Klient, który nie otrzyma jasnej informacji na temat dostępności produktu, będzie próbował ponawiać zamówienie. Poprzez słowo ‘klient’ należy tu jednak rozumieć system planowania zamówień po stronie klienta. Przedsiębiorstwa FMCG opierają swoje plany produkcji i dystrybucji o mechanizm MRP. Częstokroć również i klienci dysponują systemami ERP, które na podstawie aktualnego zapasu generują wymagane zamówienia. Również i tym przypadku kalkulacja może opierać się o MRP. Brak realizacji zamówienia skutkuje brakiem awizacji towaru, a więc w oczach systemu ERP konieczne jest ponowne wygenerowanie zamówienia. Kwestią dyskusyjną pozostaje to, czy to samo zamówienie złożone powtórnie powinno n-krotnie powodować spadek OFR. Jest to jednak problem, który przeanalizować należy biorąc pod uwagę aspekty biznesowe, dlatego też nie zostanie omówiony w tej pracy.</p>
<p>Kolejnym negatywnym skutkiem jest również to, że klienci, którzy doświadczyli oddziaływania braku dostępności produktu mogą próbować zabezpieczyć się na takie sytuacje w przyszłości. Kiedy więc towar stanie się powtórnie dostępny, składać będą wyższe zamówienia, by zbudować bufor bezpieczeństwa po stronie swojego zapasu. To zaś skutkuje jeszcze wyższymi zamówieniami, co przedłuża okres destabilizacji łańcucha dostaw po stronie dostawcy.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 14 - Wykres HIT, zamówienia składane powyżej prognozy sprzedaży</p>
<p>Na wykresie HIT widać również, że praktycznie przez cały okres trwania symulacji, zapas znajdował się znacząco poniżej progu replenishmentu. Przeciętnie były to 1142 jednostki. Jest to więc spadek aż o 25% w stosunku do scenariusza wyjściowego. W scenariuszu tym nie istnieje towar nierotujący, zaś średnie pokrycie wynosi 4,35 dnia. Są to wartości więcej niż alarmujące.</p>
<p>Co wymaga komentarza, to fakt, ze pomimo iż magazyn doświadczył trzech okresów niedostępności towaru, to momentów, w których potencjalnie mogło dojść do wyczerpania zapasu (kiedy był on krytycznie niski, poniżej 30% progu replenishmentu) było znacząco więcej. Łatwo wyobrazić sobie scenariusz, w którym część jednostek magazynowych zostaje uszkodzona, lub okazuje się obarczona defektami i niemożliwa jest sprzedaż części wolumenu. W takim przypadku skutki niskiego stanu magazynowego byłyby jeszcze bardziej dotkliwe.</p>
<p>Możliwe jest więc zauważenie znakomitego wpływu jakości prognozy sprzedaży na łańcuch dostaw oraz badana wskaźniki. Skutki te będą tym bardziej dotkliwe, im dłużej trwać będzie błędne prognozowanie sprzedaży. Potwierdza to jednocześnie hipotezę nr 2 postawioną w rozdziale 1.3.</p>
<h3 id="scenariusz-2-zmiana-minimalnego-wolumenu-produkcji">Scenariusz 2 – Zmiana minimalnego wolumenu produkcji</h3>
<p>Aby przeanalizować wpływ minimalnego wolumenu produkcji na funkcjonowanie łańcucha dostaw należy na początku zrozumieć z czego wynika potrzeba definiowania takiej wielkości.</p>
<p>Obecnie produkcja jakiegokolwiek artykuły masowego użytku, nawet pozornie tak nieskomplikowanego jak pampers, odbywa się przy użyciu zautomatyzowanych, wysokowydajnych linii produkcyjnych. Łączy je to, że aby osiągnąć maksymalną wydajność (prędkość produkcji) musi upłynąć określony czas od ich startu. Kontrolując wszystkie parametry produkcji, automatyka pozwala na stopniowe zwiększenie prędkości działania linii produkcyjnej, aż do maksymalnej wielkości. Celem każdej fabryki produkcyjnej jest oczywiście maksymalizacja produkcji w jednostce czasu. Oznacza to, że z punktu widzenia fabryki najopłacalniej produkować jednorazowo wysokie partie, gdyż maksymalizowany jest wtedy czas, kiedy linia działa z pełną wydajnością. Oczywiście to, co aktualnie produkuje fabryka nie jest podyktowane jedynie zabieganiem o jak najlepsze wskaźniki produkcyjne, lecz faktyczną potrzebną. Dlatego istnieje konieczność odnalezienia pewnego kompromisu pomiędzy zaspokajaniem dynamicznie zmieniających się zapotrzebowań, a dokonywaniem produkcji opłacalnych wolumenów.</p>
<p>Drugim czynnikiem, który wymusza zatrzymywanie i ponowne startowanie linii produkcyjnej jest zmiana części automatyki. Jako element optymalizacji kosztów jedna linia produkcyjna zazwyczaj wykorzystywana jest do produkcji zróżnicowanych artykułów. Mogą one różnić się rozmiarem, opakowaniami, bądź materiałami wykorzystywanymi do ich wytwarzania. Każda taka zmiana nieodzownie związana jest ze zmianą określonych modułów linii, które stanowią część automatyki.</p>
<p>Podobnie jak wiele innych systemów, linia produkcyjna wraz z załogą techników, którzy ją obsługują przemierzają drogę, którą określa krzywej nauki<a href="#fn41" class="footnote-ref" id="fnref41"><sup>41</sup></a>. Na końcu tej drogi osiągane są minimalne czasy zmian automatyki, najniższy procent odpadów produkcyjnych, a także najwyższa wydajność maksymalna linii. Jednak nawet w takim przypadku istnieje potrzeba określania minimalnego wolumenu produkcji, zależnego od rodzaju produktu. Jest to niezbędny element kontroli kosztów, bez którego nie może obyć się żadne przedsiębiorstwo. Oczywiście wolumeny te będę różnić się w zależności od produktów, technologii wytwarzania, czy samych linii produkcyjnych. Celem niniejszej pracy jest jednak określenie, jak duży wpływ ma minimalny wolumen produkcji wpływa na łańcuch dostaw.</p>
<p>Wyniki dla wszystkich przypadków omówionych w rozdziale 4.3.1. oparte były o założenie, że produkcja podejmowana jest, gdy zapotrzebowania przekraczają zdefiniowaną jednostkę zaokrąglenia, jedną paletę. W tym rozdziale zostaną zbadane dwa znacząco różne przypadki, gdy jednostka zaokrąglenia wynosi odpowiednio 3 palety i 10 palet.</p>
<p>W analizowanych przykładach wyeliminowano wpływ niedokładności prognozy sprzedaży. Zakłada się, że zamówienia składane są zgodnie z prognozą w systemach planowania. Nawet w pierwszym wypadku, uwidacznia się wpływ zwiększenia minimalnego wolumenu produkcji. Wskaźniki zwarte w tabeli 6 ujawniają nieznaczny spadek OFR, wyniósł on 97,40%. Ciągle jest to wysoki poziom, który mieści się w ramach potencjalnych oczekiwań. W czasie trwania 16-tygodniowej symulacji jedynie podczas dwóch dni istniał problem wyczerpania się zapasów w magazynie. Uwagę zwrócić należy jednak na wskaźnik DFC, który spadł do poziomu 5,59 dnia. Wprowadzenie bardziej rygorystycznych wymagań w stosunku do produkcji przekłada się zatem bezpośrednio na przeciętne pokrycie materiałowe w magazynie.</p>
<p>Tabela 6 - Zestawienie wskaźników, niski minimalny wolumen produkcji</p>
<table>
<thead>
<tr class="header">
<th><strong>OFR</strong></th>
<th><strong>INV</strong></th>
<th><strong>DFC</strong></th>
<th><strong>IST</strong></th>
<th><strong>DS</strong></th>
<th><strong>NPI</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>97,40%</td>
<td>1346,37</td>
<td>5,59</td>
<td>17,17</td>
<td>0,00</td>
<td>0,00%</td>
</tr>
</tbody>
</table>
<p>Analiza wykresu HIT nie przynosi zaskoczeń. Przeciętny zapas wyniósł 1346 jednostek w stosunku do progu replenishmentu 1750 jednostek. Przez większość czasu zapas znajduje się poniżej progu replenishmentu. Nie jest to jednak sytuacja, która świadczy o złym zdrowiu łańcucha dostaw. Pomimo spadku przeciętnego zapasu i zmniejszenia się pokrycia materiałowego, magazyn ciągle był w stanie zrealizować ponad 97% zamówień. Z uwagi na dwukrotne całkowite wyczerpanie się zapasu wskaźnik martwego zapasu (towaru nierotującego), a także udział zapasu nierotującego w stosunku do przeciętnego zapasu posiadają wartości zerowe.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 15 - Wykres HIT, niski minimalny wolumen produkcji</p>
<p>Sytuacja zmienia się jednak znakomicie, kiedy minimalny wolumen produkcji wynosi 10 palet. Mimo sprzedaży zgodnej z prognozą zaobserwować można znaczący spadek procentu zrealizowanych zamówień. Wyniósł on zaledwie 90,18%. Znacząco obniżył się przeciętny zapas - odnotowany został spadek do poziomu 1147 jednostek. Przełożyło się to rzecz jasna na przeciętne pokrycie, które spadło do poziomu 4,71 dnia, podczas gdy próg replenishmentu na poziomie 1750 jednostek zapewnić powinien przeciętne pokrycie 8 dni zapotrzebowania.</p>
<p>Tabela 7 - Zestawienie wskaźników, wysoki minimalny wolumen produkcji</p>
<table>
<thead>
<tr class="header">
<th><strong>OFR</strong></th>
<th><strong>INV</strong></th>
<th><strong>DFC</strong></th>
<th><strong>IST</strong></th>
<th><strong>DS</strong></th>
<th><strong>NPI</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>90,18%</td>
<td>1147,99</td>
<td>4,71</td>
<td>20,14</td>
<td>0,00</td>
<td>0,00%</td>
</tr>
</tbody>
</table>
<p>Dużo więcej informacji przynosi jednak analiza wykresu HIT (rysunek 16). Najważniejsza różnicą w stosunku do przypadku, kiedy minimalny wolumen produkcyjny wynosił 3 palety jest znaczące obniżenie się częstotliwości replenishmentu. W pierwszym przypadku przeciętnie co 2 dni następowało przyjęcie nowej partii towaru do centrum dystrybucyjnego. W tym przypadku zaś dostawy następowały przeciętnie co 6-7 dni. Jest to związane ze sposobem, w jaki działa mechanizm MRP. Zapotrzebowania produkcyjne były oczywiście widoczne z należytym wyprzedzeniem, jednak fabryka nie podejmowała produkcji aż do momentu, kiedy łączne zapotrzebowania nie przekroczyły progu minimalnego wolumenu produkcyjnego. Oznacza, że to po uwolnieniu każdej partii produkcyjnej konieczne było oczekiwanie, aż kolejne zapotrzebowania nie skumulują się do poziomu 10 palet. W naturalny sposób wydłużenie cyklu produkcyjnego przekłada się na spadek częstotliwości dostaw.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 16 - Wykres HIT, wysoki minimalny wolumen produkcji</p>
<p>Drugą informacją, którą odczytać można z wykresu jest to, że sam mechanizm MRP działał jak najbardziej poprawnie. Każdorazowa dostawa towaru do centrum dystrybucyjnego powodowała skok zapasu do wartości pożądanej – progu replenishmentu. Stan ten jednak nie utrzymywał się zbyt długo. W miarę realizacji kolejnych zamówień klientów spadał on niemal do zera przed następną dostawą. Wielokrotnie zapas bliski był wyczerpania, co oznacza niską elastyczność łańcucha dostaw w przypadku zwiększonego popytu. W przypadku nałożenia na ten przypadek efektu wysyłek przekraczających prognozę sprzedaży, doprowadziłoby to do jeszcze bardziej drastycznego spadku wskaźnika OFR.</p>
<p>Widoczna jest więc bezpośrednia zależność pomiędzy wielkością partii produkcyjnej, a badanymi wskaźnikami KPI. Ramy tego problemu można jednak rozszerzyć. Chociaż istotnie najczęstszym powodem spadku częstotliwości dostaw jest spadek częstotliwości produkcji, zaistnieć mogą inne okoliczności, które owocować będą takim samym rezultatem. Generycznym problemem w tym przypadku jest częstotliwość, w jakim towar opuszcza fabrykę. Założonym ograniczeniem jest zaś zdławiona produkcja. Przeanalizujmy jednak przypadek, kiedy produkcja odbywa się bez żadnych ograniczeń (tj. w momencie, gdy kalkulacja MRP określa zapotrzebowania, możliwe jest wyprodukowanie partii towaru niezależnie od wolumenu zapotrzebowań), ograniczeniem zaś pozostaje transport. Niemożliwe jest wysłanie kolejnego środka transportu, jeżeli wolumen towaru wyprodukowanego w fabryce nie zapełni w pełni przestrzeni ładunkowej pojazdu. W tym przypadku bez znaczenia pozostaje fakt, że towar został wyprodukowany zgodnie z datą zapotrzebowania. Nie opuści on bowiem fabryki do momentu, gdy łączny wolumen nie przekroczy określonego progu. Jeżeli zaś próg ten będzie wynosił również 10 palet, do czynienia będziemy mieli z analogiczną sytuacją. Ważnym wnioskiem jest więc to, że nie tylko minimalny wolumen produkcji może doprowadzić do sytuacji omawianej w tym rozdziale.</p>
<p>W praktyce najczęściej spotykane jest skrzyżowanie tych dwóch problemów. Ograniczeniami są zarówno minimalny wolumen produkcji jak i wypełnienie całkowicie środka transportu. Dotyczy to oczywiście większych przedsiębiorstw, które samodzielnie produkują towar oraz bez udziału podmiotów zewnętrznych organizują jego transport pomiędzy ogniwami w swojej sieci. Problem ten zazwyczaj nie dotyka mniejszych przedsiębiorstw. Jeżeli przeciętny wolumen produkcji jest niski zlecają one produkcję innym firmom, zaś kiedy problemem jest transport, z powodzeniem korzystać mogą z tradycyjnych usług spedycyjnych LTL<a href="#fn42" class="footnote-ref" id="fnref42"><sup>42</sup></a>.</p>
<p>Ze scenariusza tego można wyciągnąć kilka wniosków. Po pierwsze – w przypadku (przeciętnie) tygodniowego cyklu produkcyjnego, próg replenishmentu zdefiniowany na poziomie 8 dni jest zdecydowanie zbyt niski. Pragnąc zabezpieczyć się przez niepożądanymi skutkami cyklicznej produkcji należy (biorąc pod uwagę średni czas dostawy) ustalić parametry bezpieczeństwa tak, by kompensowały one brak dostaw, kiedy są one w idealnym scenariuszu wymagane.</p>
<p>Po drugie – należy unikać sytuacji, kiedy produkcja odbywa się cyklicznie. Rozwiązań tego problemu może być kilka – oczywistym jest zmniejszenie minimalnej partii produkcyjnej. Jeżeli jednak nie jest to możliwe należy przeanalizować, czy nie jest możliwe np. skonsolidowanie portfolio produktów w taki sposób, by oferować ich mniejszą ilość. Przełoży się to na mniejszą ilość zmian automatyki na liniach produkcyjnych. Inną formą konsolidacji może być współdzielenie produktów pomiędzy większą ilością ogniw w sieci. Bardzo częstym przypadkiem jest sprzedawanie tego samego produktu w kilku krajach. O ile dla konsumenta produkt pozostaje niezmienny, dla przedsiębiorstwa produkcyjno-dystrybucyjnego każdorazowo są to inne produkty, ponieważ wymagają one innych etykiet umieszczanych na opakowaniach. W przypadku, gdy możliwe jest umieszczenie na etykiecie zwiększonej ilości języków należy z tej możliwości skorzystać. W ten sposób zwiększymy łączne zapotrzebowania na dany SKU<a href="#fn43" class="footnote-ref" id="fnref43"><sup>43</sup></a>. Oznacza to więc, że nawet w przypadku wysokiego minimalnego wolumenu produkcji szybciej osiągniemy wymagane zapotrzebowania, jeżeli spływać będą one nie z jednego, lecz z kilku ogniw w sieci.</p>
<h3 id="scenariusz-3-zmiana-strategii-replenishmentu">Scenariusz 3 – Zmiana strategii replenishmentu</h3>
<p>Standardowo SAP umożliwia zdefiniowanie pożądanego progu replenishmentu z wykorzystaniem dwóch strategii – <em>safety stock</em> (SS) oraz <em>safety time</em> (ST). SS jest statyczną wartością wyrażoną w jednostek produktu. Jeżeli prognozowany zapas spadnie poniżej tej wartości automatycznie wygenerowany zostanie replenishment o wielkości umożliwiającej powrót zapasu do tej wartości. ST, nazywany też na potrzeby tej pracy parametrem dynamicznym, określa ilość dni w przód, na które pragniemy zapewnić pokrycie materiałowe. Oznacza to, że może on przybierać diametralnie różne wartości, jeżeli wyrazić go będziemy chcieli za pomocą jednostek produktu. Będą one zależne od prognozy sprzedaży oraz zamówień aktualnie składanych przez klientów.</p>
<p>Każda z tych strategii niesie ze sobą określone korzyści i niebezpieczeństwa. SS wymaga cyklicznych rewizji. Z powodzeniem możemy wyobrazić sobie sytuację, kiedy sprzedaż danego produktu znakomicie rośnie w czasie. Statyczny próg replenishmentu, który na początku zapewniał pokrycie na tydzień, z biegiem czasu może zapewniać pokrycie na np. 2 dni. Zdecydowanie nie jest to sytuacja pożądana. Inne ryzyko związane z wykorzystaniem SS to fakt, że będzie on generować zapotrzebowania materiałowe mimo braku innych elementów MRP – takich jak prognoza sprzedaży czy zamówienia klientów. Tutaj niebezpiecznym przypadkiem będzie więc sytuacja, kiedy określony produkt jest wycofywany ze sprzedaży. Pomimo braku jakichkolwiek realnych zapotrzebowań <em>safety stock</em> wygeneruje niepotrzebne replenishmenty.</p>
<p>ST również nie jest strategią pozbawioną wad. Nierzadką sytuacją jest błędna prognoza sprzedaży, bądź też prognoza sprzedaży zupełnie nieobecna w systemach planowania wskutek np. problemu z interfejsami pomiędzy różnymi systemami wykorzystywanymi przez przedsiębiorstwo. W takim przypadku SS ciągle generuje zapotrzebowania zapewniając określone pokrycie materiałowe, podczas gdy ST całkowicie zabija przepływ zapotrzebowań w sieci. Inną sytuacją jest np. znacząco zwiększona prognoza sprzedaży z powodu aktywności promocyjnej. Jeżeli aktywność ta trwać będzie dłuższy czas, ST znacząco przyspieszy pojawianie się zapotrzebowań w niższych ogniwach sieci, co niekoniecznie odzwierciedlać będzie moment, kiedy faktycznie muszą być wyprodukowane.</p>
<p>Analizowane dwa przypadki nie są jednak przypadkami brzegowymi omówionymi wcześniej. Doświadczenie ma na celu pokazanie, jak zachowują się parametry łańcucha dostaw, kiedy prognoza popytu nie charakteryzuje się dużym odchyleniem standardowym, wysyłki zgodne są z oczekiwanym popytem i nie mają miejsca żadne błędy wynikające z winy człowieka, czy też systemów informatycznych. Jedyną analizowaną zmienną jest strategia replenishmentu.</p>
<p>Pierwszy analizowany przypadek analogiczny jest od scenariusza 1, kiedy wysyłki odbywały się zgodnie z prognozą sprzedaży. Wyniki nie są zaskoczeniem. Wystąpiły dwa okresy, kiedy produkt istniały problemy z dostępnością produktu. Pierwszy pomiędzy 17.01.2018 a 19.01.2018, drugi zaś 22.-2.2018, kiedy to nie zostało zrealizowane największe zamówienie – niemożliwe było wysłanie aż 611 jednostek (rysunek 17).</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 17 - Zestawienie zrealizowanych oraz niezrealizowanych zamówień z prognozą sprzedaży dla statycznego parametru bezpieczeństwa</p>
<p>Przełożyło się to na OFR na poziomie 96,46% (tabela 8). Nie jest to wartość bardzo dobra, lecz nie powinna jednocześnie budzić nadmiernego niepokoju.</p>
<p>Tabela 8 - Zestawienie wskaźników, statyczny parametr bezpieczeństwa</p>
<table>
<thead>
<tr class="header">
<th><strong>OFR</strong></th>
<th><strong>INV</strong></th>
<th><strong>DFC</strong></th>
<th><strong>IST</strong></th>
<th><strong>DS</strong></th>
<th><strong>NPI</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>96,46%</td>
<td>1580,04</td>
<td>6,48</td>
<td>14,63</td>
<td>0,00</td>
<td>0,00%</td>
</tr>
</tbody>
</table>
<p>Średni inwentarz wyniósł 1580 jednostek, co oznaczało średnie pokrycie na poziomie 6,48 dnia oraz rotację na poziomie 14,63. Aż do 01.03.2018 zapas znajdował się przez większość czasu poniżej progu replenishmentu, dopiero później ustabilizował się i zaczął oscylować w jego granicach. Są to więc wyniki, które odpowiadają przytoczonemu już scenariuszowi nr 1.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 18 - Wykres HIT, statyczny parametr bezpieczeństwa</p>
<p>Aby możliwe było dokładne porównanie tych dwóch strategii w drugim przypadku (strategia ST) wykorzystana została dokładnie ta sama prognoza sprzedaży oraz dokładnie te same zamówienia klientów. Zaowocowało to wskaźnikiem OFR wynoszącym 98,63% (tabela 9), a więc aż o 2,17% wyższym. Warto też zwrócić uwagę na średni zapas, który wyniósł przeciętnie 2039 jednostek, a więc aż o 459 jednostek więcej niż w pierwszym przypadku.</p>
<p>Tabela 9 - Zestawienie wskaźników, dynamiczny parametr bezpieczeństwa</p>
<table>
<thead>
<tr class="header">
<th><strong>OFR</strong></th>
<th><strong>INV</strong></th>
<th><strong>DFC</strong></th>
<th><strong>IST</strong></th>
<th><strong>DS</strong></th>
<th><strong>NPI</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>98,63%</td>
<td>2039,14</td>
<td>8,57</td>
<td>11,34</td>
<td>0,00</td>
<td>0,00%</td>
</tr>
</tbody>
</table>
<p>Wskaźniki te znajdują namacalne odzwierciedlenie w zamówieniach, które możliwe były do zrealizowania. O ile również i w tym przypadku centrum dystrybucyjne doświadczyło ograniczonej dostępności produktu pomiędzy 17.01.2018 a 19.01.2018, możliwe było zrealizowanie wszystkich pozostałych zamówień podczas trwania całej symulacji. Jest to więc zauważalna różnica.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 19 - Zestawienie zrealizowanych oraz niezrealizowanych zamówień z prognozą sprzedaży dla dynamicznego parametru bezpieczeństwa</p>
<p>O wiele wyższy średni zapas widoczny jest też na wykresie HIT. Praktycznie przez cały czas trwania symulacji dla tej samej prognozy sprzedaży magazyn dysponował zauważalnie większą ilością towaru niż w przypadku strategii SS. Oznacza to większą elastyczność oraz gotowość realizowania zamówień, które wykraczają znacząco poza prognozę sprzedaży.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 20 - Wykres HIT, dynamiczny parametr bezpieczeństwa</p>
<p>Można więc wyciągnąć posunąć się do stwierdzenia, ze <em>safety time</em> jest strategią bezpieczniejszą. Jeżeli dla łańcucha dostaw kluczowym wskaźnikiem jest procent zrealizowanych zamówień klientów, systemy planowania powinny wykorzystać ST jako strategię replenishmentu. Należy jednak być ostrożnym. Gotowość do realizacji zamówień, które niemożliwe byłyby do zrealizowania w przypadku <em>safety stock</em> obarczona jest większym średnim zapasem. Jest to oczywiście korzystne z punktu widzenia elastyczności oferowanej działom sprzedaży oraz większym marginesem błędu na jakie przedsiębiorstwo może pozwolić sobie podczas przygotowywania prognozy sprzedaży. Jednak w przypadku, gdy takie dodatkowe zabezpieczenia nie są niezbędne, ponieważ z dużą precyzją jesteśmy w stanie przewidzieć zachowanie klientów, wykorzystując dynamiczny próg replenishmentu obarczymy ogniwa niższego rządu zwiększonymi oraz przyspieszonymi zapotrzebowaniem, które zaowocują wzrostem DS/NPI oraz spadkiem rotacji w ogniwach wyższego rzędu.</p>
<p>Oznacza to więc, że wybór pomiędzy tymi dwoma strategiami wymaga przeanalizowania sposobu, w jaki funkcjonuje przedsiębiorstwo i zależny jest od konkretnego przypadku. Może okazać się, że w ramach jednej firmy opłacalnie będzie dla części portfolio produktów wykorzystać ST, dla pozostałej zaś – SS.</p>
<h3 id="scenariusz-4-wpływ-przypadków-losowych-na-łańcuch-dostaw">Scenariusz 4 – Wpływ przypadków losowych na łańcuch dostaw</h3>
<p>Ostatni ze scenariuszy testowych stoi nieco w opozycji do pozostałych. Scenariusze 1-3 miały na celu porównanie dwóch lub więcej przypadków, w celu określenia, jaką strategię warto przyjąć by osiągnąć określony cel. Scenariusz 4 jest zaś odzwierciedleniem przypadków losowych, które niemożliwe są do wyeliminowania żadnymi systemowymi zmianami. Badane zostały tutaj dwa przypadki – zablokowanie produktu w centrum dystrybucyjnym oraz uszkodzenie się linii produkcyjnej. Za każdym razem celem symulacji było określenie jak bardzo dany wypadek losowy wpłynął na łańcuch dostaw oraz kiedy powrócił on do swojego oryginalnego stanu.</p>
<p>Łańcuchy dostaw to nic innego jak systemy zajmujące się produkcją, a następnie fizycznym przemieszczeniem produktu, tak by dostępny był w odpowiednim czasie i w odpowiedniej ilości, by zaspokoić zapotrzebowania odpowiednich klientów. Wszystkie fizyczne manipulacje produktami obarczone są pewnym ryzykiem. Towar może zostać uszkodzony na wielu etapach. W magazynie, podczas umieszczana na regale, przez nieuważnego operatora wózka widłowego, w transporcie, wskutek złego zabezpieczenia towaru, czy też z uwagi na niską jakość poszycia środka transportu i wodę, która przedostała się do przestrzeni ładunkowej podczas transportu. Podobnie sama produkcja – wyprodukowany towar może być wadliwy, co początkowo nie zostanie wykryte przy kontroli jakości. Scenariuszy jest naprawdę wiele. W branży FMCG transportowane towary rzadko kiedy są drogie i najczęściej wady produkcyjne, czy fizyczne uszkodzenie sprawią, że muszą być one całkowicie wyeliminowane ze sprzedaży.</p>
<p>Warto przypomnieć jaką strukturę posiada analizowany łańcuch dostaw (rysunek 5). Ogniwo w którym następuje sprzedaż i w którym symulowany jest zanik zapasu (sygnatura 2621) zasilane jest z fabryki (sygnatura 4853). Po drodze wymagany jest jednak przeładunek oraz kontrola celna (ogniwo o sygnaturze 2751). Dotarcie towaru z fabryki do kontroli celnej zajmuje przeciętnie 10 dni roboczych, zaś przeładunek i kontrola celna trwają przeciętnie 2 dni robocze. Teoretycznie więc pierwsze zasilenie możliwe po 12 dniach roboczych od momentu zaistnienia bodźca.</p>
<p>Tabela 10 - Zestawienie wskaźników, uszkodzenie towaru wskutek katastrofy naturalnej</p>
<table>
<thead>
<tr class="header">
<th><strong>OFR</strong></th>
<th><strong>INV</strong></th>
<th><strong>DFC</strong></th>
<th><strong>IST</strong></th>
<th><strong>DS</strong></th>
<th><strong>NPI</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>95,96%</td>
<td>1456,47</td>
<td>5,90</td>
<td>15,87</td>
<td>0,00</td>
<td>0,00%</td>
</tr>
</tbody>
</table>
<p>Pierwszy przypadek zakłada uszkodzenie towaru wskutek katastrofy naturalnej. Efektem jest całkowite uniemożliwienie sprzedaży dotychczas posiadanego zapasu. W symulacji miało to miejsce 24.02.2018, gdzie widoczny jest spadek zapasu do poziomu 0. Kompletny powrót do stanu wyjściowego, a więc do osiągniecia zapasu powyżej progu replenishmentu, trwał 18 dni. Dopiero 14.03.2018 miało miejsce zasilenie, które zregenerowało łańcuch dostaw. Na wykresie HIT (rysunek 21) widoczne są jednak dostawy przed tą datą. Jest to towar, który został wysłany z ogniw zasilających jeszcze przed momentem wystąpienia symulowanego zaburzenia. Dlatego też dostawy te nie sprawiają, że zapas osiąga poziom progu replenishmentu. W momencie planowania tych wysyłek prognoza przyszłego stanu magazynowego była znacząco różna od rzeczywistości.</p>
<p>Czas reakcji dla tego przypadku wyniósł aż 18 dni. Jest to znacząco więcej niż przytoczone wcześniej 12 dni roboczych, które zajmuje transport produktu pomiędzy ogniwami. Jakie jest zatem pochodzenie tej wartości? Przede wszystkim sama ponowna produkcja towaru związana jest z upłynięciem określonego czasu. Może zdarzyć się też tak, że konieczne będzie oczekiwanie na następny cykl produkcyjny danego dobra. Może też nie być możliwe wykonanie takiej produkcji z powodu braku surowców, które zgodnie z zapotrzebowaniami i koncepcją dostaw JIT<a href="#fn44" class="footnote-ref" id="fnref44"><sup>44</sup></a> powinny zostać doręczone znacznie później. Po samej produkcji konieczne jest wykonanie kontroli jakości. Dopiero po stwierdzeniu, że wyprodukowana partia towaru może zostać sprzedana należy zaplanować wysyłkę i oczekiwać na jej załadunek. Jak więc widać – łańcuch dostaw cechuje się pewną bezwładnością. W drogach wyjątku można i należy minimalizować te straty czasu, lecz nie zawsze jest to możliwe. Kiedy towar wyruszy już w drogę ciągle możliwe są opóźnienia środków transportu, a także opóźnienia przeładunku, rozładunku, czy samej kontroli celnej. Wszystkie te możliwości są częścią stworzonej symulacji komputerowej. Niestety analizowane dane nie dają odpowiedzi na pytanie, które z wymienionych ewentualności zaistniały akurat w tym przypadku, jednak istotne jest to, że każda z tych przeszkód mogłaby zaistnieć również w niesymulacyjnych warunkach.</p>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 21 - Wykres HIT, uszkodzenie towaru wskutek katastrofy naturalnej</p>
<p>Efektem symulowanej katastrofy naturalnej była możliwość zrealizowania 95,96% zamówień klientów. Należy jednak zwrócić uwagę na bardzo niski zapas w dniach 28.02.2018 – 03.03.2018 oraz 06.03.2018 – 12.03.2018. Gdyby centrum dystrybucyjne akurat w tym okresie doświadczyło wzmożonego popytu elastyczność realizacji dodatkowych zamówień byłaby znikoma.</p>
<p>Tabela 11 - Zestawienie wskaźników, efekt uszkodzenia linii produkcyjnej</p>
<table>
<thead>
<tr class="header">
<th><strong>OFR</strong></th>
<th><strong>INV</strong></th>
<th><strong>DFC</strong></th>
<th><strong>IST</strong></th>
<th><strong>DS</strong></th>
<th><strong>NPI</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>96,48%</td>
<td>1680,33</td>
<td>7,14</td>
<td>13,76</td>
<td>0,00</td>
<td>0,00%</td>
</tr>
</tbody>
</table>
<p>Drugi z badanych przypadków zakładał zatrzymanie linii produkcyjnej na 8 dni, w okresie 28.02.2018 – 07.03.2018. Również i w tym przypadku ujawniła się bezwładność łańcucha dostaw. Mimo zatrzymania się linii produkcyjnej 28.02.2017 efekt tego w centrum dystrybucyjnym był widoczny dopiero 22.02.2017, a więc prawie miesiąc później. Jest to pochodną dwóch faktów:</p>
<ol type="1">
<li><p>W momencie uszkodzenia się linii produkcyjnej w tranzycie znajdował się towar, który doręczony został już po awarii linii</p></li>
<li><p>Próg replenishmentu został zdefiniowany na około 8 dni pokrycia wysyłek, co jeszcze bardziej zniwelowało bezpośredni efekt braku replenishmentów</p></li>
</ol>
<p><span class="chart">[CHART]</span></p>
<p>Rysunek 22 - Wykres HIT, efekt uszkodzenia linii produkcyjnej</p>
<p>Jak odczytać można z wykresu HIT – ośmiodniowy przestój linii produkcyjnej zaowocował jedynie czterema dniami, kiedy produkt był niedostępny w magazynie, z które prowadzone są wysyłki. Można spodziewać się jednocześnie, że gdyby próg replenishmentu zapewniał większe pokrycie materiałowe, okres niedostępności produktu można byłoby zminimalizować. W tym przypadku możliwe było zrealizowanie 96,48% zamówień.</p>
<p>Gdyby taka sytuacja miała miejsce w rzeczywistości, tygodniowy okres przestoju linii produkcyjnej oznaczałby znaczące skumulowanie się zapotrzebowań produkcyjnych.</p>
<h1 id="podsumowanie">Podsumowanie</h1>
<h2 id="weryfikacja-postawionych-tez">Weryfikacja postawionych tez</h2>
<h2 id="wnioski-końcowe">Wnioski końcowe</h2>
<h1 id="załączniki">Załączniki</h1>
<h2 id="standard-tworzenia-sygnatur-elementów-mrp-w-symulacji">Standard tworzenia sygnatur elementów MRP w symulacji</h2>
<p>Z praktycznego punktu widzenia dobrą praktyką jest tworzenie sygnatur generowanych MRP elementów. Umożliwia to łatwe tworzenie referencji pomiędzy poszczególnymi elementami. Jest to istotne również z punktu widzenia użytkownika systemu ERP, który widząc sygnaturę o określonej budowie (zaczynającą się od odpowiedniego znaku alfanumerycznego, posiadającą ściśle określoną długość) jest w stanie zidentyfikować związany z nią MRP element. Odnosząc się również do architektury aplikacji, która oparta może być na relacyjnej bazie danych, jest to rozwiązania pożądane.</p>
<p>O ile implementacja sygnatur nie jest niezbędna w bieżącym zakresie pracy, może być bardzo pomocna w przypadku rozszerzenia ram symulacji.</p>
<p>Tabela 12 - Spis sygnatur elementów MRP</p>
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
<p>Tabela 13 - Spis znaczników czasu elementów MRP</p>
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
<li id="fn30"><p>OTD – On Time Delivery, wskaźnik informujący o tym, jaki procent zamówień zostało zrealizowanych na czas, do daty zdefiniowanej przez klienta<a href="#fnref30" class="footnote-back">↩</a></p></li>
<li id="fn31"><p>SAMBC – Service As Measured By Customer, wskaźnik informujący o poziomie zadowolenia klienta ze współpracy z danym dostawcą<a href="#fnref31" class="footnote-back">↩</a></p></li>
<li id="fn32"><p>3PL – Third Party Logistics, podmioty trzecie świadczące usługi logistyczne takie jak magazynowanie, przeładunki czy transport dla zewnętrznych podmiotów gospodarczych<a href="#fnref32" class="footnote-back">↩</a></p></li>
<li id="fn33"><p>WMS – Warehouse Management System, systemy informatyczne wspomagające zarządzanie magazynem, decydujące o przepływie materiału wewnątrz magazynu<a href="#fnref33" class="footnote-back">↩</a></p></li>
<li id="fn34"><p>FIFO – First In First Out, kolejka w której pierwszy towar, który został umieszczony w magazynie jest również pierwszym, który zostaje zaplanowany do wydania<a href="#fnref34" class="footnote-back">↩</a></p></li>
<li id="fn35"><p>LIFO – Last In First Out, kolejka w której ostatni towar, który został umieszczony w magazynie jest pierwszym, który zostaje zaplanowany do wydania<a href="#fnref35" class="footnote-back">↩</a></p></li>
<li id="fn36"><p>FEFO – First Expirienced First Out, kolejka w której towar o najkrótszej dacie przydatności jest pierwszym, który zostaje zaplanowany do wydania<a href="#fnref36" class="footnote-back">↩</a></p></li>
<li id="fn37"><p>HIT – Historical Inventory Tracking, zapis stanu zapasu oraz parametrów bezpieczeństwa w perspektywie czasu<a href="#fnref37" class="footnote-back">↩</a></p></li>
<li id="fn38"><p>EDI – Electronic Data Interchange, zautomatyzowana wymiana danych pomiędzy systemami informatycznymi w oparciu o ustalony format komunikatów<a href="#fnref38" class="footnote-back">↩</a></p></li>
<li id="fn39"><p>Big data – duże, zmienne i różnorodne zbiory danych o utrudnionej analizie<a href="#fnref39" class="footnote-back">↩</a></p></li>
<li id="fn40"><p>MAD - Mean Average Deviation, średnie odchylenie standardowe<a href="#fnref40" class="footnote-back">↩</a></p></li>
<li id="fn41"><p>Krzywa nauki – ang. learning curve, graficzna reprezentacja wyników nauki, które zmieniają się wraz ze zdobywanym doświadczeniem<a href="#fnref41" class="footnote-back">↩</a></p></li>
<li id="fn42"><p>LTL – less than truckload, fracht małego rozmiaru, realizowany na zasadzie konsolidacji przesyłek, często z wykorzystaniem środków transportu mniejszych od klasycznych ciężarówek<a href="#fnref42" class="footnote-back">↩</a></p></li>
<li id="fn43"><p>SKU – stock keeping unit, jednostka magazynowa, oznaczenie produktu wykorzystywane w ruchu magazynowym, wewnątrz danego przedsiębiorstwa<a href="#fnref43" class="footnote-back">↩</a></p></li>
<li id="fn44"><p>JIT – just in time, frachty charakteryzujące się wysoką precyzją czasu doręczenia, mają za zadanie zminimalizować lub nawet całkowicie wykluczyć magazynowanie towaru<a href="#fnref44" class="footnote-back">↩</a></p></li>
</ol>
</section>
</body>
</html>

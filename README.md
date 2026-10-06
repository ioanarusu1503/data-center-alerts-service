Argumentare design patterns folosite

Singleton (Baza de Date) Database.java
Implementare: Utilizat in clasa Database pentru a garanta o instanta unica a seturilor de date (servere, grupuri, alerte).

Avantaje: Intr-un sistem de management trebuie sa exista o sursa de adevar. Singleton previne instantierea
multipla a bazei de date, evitand astfel inconsistentele datelor si consumul inutil de memorie.

Command (Procesarea Functionalitatilor) Command.java
Implementare: Interfata Command si clasele concrete (ex: AddServer, AddGroup, RemoveMember, FindMember, AddMember,
FindMember, RemoveMember AddEvent) care implementeaza logica fiecarei comenzi.

Avantaje: Decupleaza clasa Main de executant. Aceasta abordare respecta principiul Open/Closed:putem adauga oricate comenzi
noi fara a modifica structura de control din Main. In plus, fiecare comanda isi gestioneaza propriile validari si exceptii specifice

Factory (Crearea Utilizatorilor) UserFactory.java
Implementare: Clasa UserFactory instantiaza obiecte de tip Admin, Operator sau User pe baza rolului citit din fisier.

Avantaje: Centralizeaza creare de obiecte dintr-o ierarhie complexa. Factory face codul mult mai curat si permite adaugarea
de noi tipuri de utilizatori fara a modifica clasele care ii utilizeaza.

Builder (Configurarea Serverelor) Server.java
Implementare: Clasa interna Builder din entitatea Server pentru gestionarea atributelor hardware (CPU, RAM, Storage) si a statusului.

Avantaje: Rezolva problema constructorilor cu prea multi parametri. Deoarece un server poate avea atribute optionale diferite,
Builder permite constructia pas cu pas a obiectului, imbunatatind lizibilitatea si prevenind erori


Concepte OOP

Ierarhia User: Clasa Admin mosteneste Operator, care la randul sau mosteneste clasa de baza User. Acest lucru permite reutilizarea
atributelor comune (name, role, email) si adaugarea de atribute specifice (department pentru Operator, clearanceLevel pentru Admin).

Exceptii Custom: Clasele MissingIpAddressException, UserException si LocationException mostenesc clasa de baza Exception din Java,
permitand crearea unui sistem de erori personalizat

Colectii Generice: In Database, seturile sunt definite generic: Set<Server>, Set<ResourceGroup> si Set<Alert>.
Acest lucru previne introducerea unor obiecte de tip gresit in colectii.

Utilizarea Optional<T>: Metoda findGroup returneaza un Optional<ResourceGroup>. Aceasta este o utilizare a unei clase
generice pentru a forta tratarea cazului in care grupul cautat nu exista, evitand astfel erorile de tip NullPointerException.

Exceptii Specifice: Fiecare eroare mentionata in enunt are o exceptie dedicata, ceea ce permite returnarea mesajelor de
eroare cu simbolurile corecte (##) in locurile potrivite.

Try-Catch Blocks: Fiecare clasa de tip Command incapsuleaza logica intr-un bloc try-catch pentru a
intercepta erorile de procesare sau de parsare fara a opri executia intregului program.

Validari de Date: Inainte de executia propriu-zisa, parametrii sunt validati (verificare IP, verificare
campuri obligatorii User), aruncand exceptii controlate in cazul datelor lipsa.
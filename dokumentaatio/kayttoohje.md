# Käyttöohje

Ohjelmaa ajetaan antamalla sille kaksi parametriä: regex ja testattava merkkijono.
Ohjelma palauttaa true, jos regex hyväksyy merkkijonon, ja false jos ei hyväksy.

Ohjelmaa voi ajaa joko kansiossa application Gradlen avulla, tai missä tahansa JAR-tiedostolla.

Gradlessa parametrit annetaan seuraavasti: `./gradlew run --args="<regex> <merkkijono>"`  
JAR:ia käytettäessä parametrit annetaan seuraavasti: `java -jar Regex.jar <regex> <merkkijono>`

Tyhjän merkkijonon voi molemmissa tilanteissa antaa kirjoittamalla `''`

## Esimerkkejä
`> ./gradlew run --args="a* aaa"`  
`true` 

`> java -jar (asd)* asddsa`  
`false`

`> java -jar a* ''`  
`true`
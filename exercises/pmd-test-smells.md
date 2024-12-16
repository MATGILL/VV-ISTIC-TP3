# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

En cours nous avons surtout parlé des règles qui concernent les tests mal conçus, comme 
[JUnitAssertionsShouldIncludeMessage](../pmd-documentation/JUnitAssertionsShouldIncludeMessage.md),
[JUnitTestsShouldIncludeAssert](../pmd-documentation/JUnitTestsShouldIncludeAssert.md),
ou encore [JUnitTestContainsTooManyAsserts](../pmd-documentation/JUnitTestContainsTooManyAsserts.md) (liste non exhaustive).

Après avoir testé un bon nombre de règle (dont certaines ont été renommées à partir de pmd 7.0.0) qui ne donnait aucun résultat
nous avons choisi de nous rabattre la règle `UnitTestContainsTooManyAsserts` (anciennement `JUnitTestContainsTooManyAsserts`) et trouvé plusieurs 
détections même avec un seuil minimal plutôt élevé (30).
Parmis ces détections plusieurs indiquaient la classe `commons-lang/src/test/java/org/apache/commons/lang3/ArrayUtilsTest.java`, en ouvrant ce 
fichier nous pouvons voir qu'en effet les méthodes de test contiennent bon nombre d'assertion, comme la méthode `testSameLengthAll` par exemple.


Nous ne proposons pas de code d'amélioration puisque nous estimons qu'il s'agit d'un faux positif et que cette méthode remplit son rôle unitaire 
et qu'il ne serait pas nécessaire de l'éclater en plusieurs tests.
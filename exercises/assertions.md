# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

### 1. 
Le test ne passe pas à cause des erreur de précision dans les calculs avec des nombre **floatant**. Ici, 3 * .4 = 1.20000...
En regardant la doc de junit on constate que pour comparer deux double il faut utiliser :
```java
assertEquals(double expected, double actual, double epsilon);
```
Avec epsilon décrit l'écart accepté entre la valeur attendu et la valeur accepté. 
on pourrait donc ré-écrire le cas de test comme ceci  :
```java
assertEquals((3 * .4), 1.2, 0.0001);
```

### 2.
`assertSame` va comparer deux objets en fonction de leurs référence grâce à l'opérateur `==`. Et `assertSame` va comparer si les deux objets sont identique. 

Ils produiront les même résultat dans un cas comme : 
```java
void test_equalsAndSame_similar(String string) {
        assertEquals(string, string); //true
        assertSame(string, string); //true
}
```

Ils ne produiront pas les même résultats dans un cas comme celui-ci : 
```java
void test_equalsAndSame_diff(String string) {
        assertEquals(new String(string), new String(string)); //true
        assertSame(new String(string), new String(string)); //false
}
```

### 3.
Comme pour marquer le code qui ne doit pas être éxécuté car on attend une Exception, on peut faire l'inverse. En utilisant le `fail` en cas d'Exception imprévu. 

```java
@Test
public void testDontThrow() {
    try {
        method();
    } catch (Exception e) {
        fail("Unexpected exception was thrown");
    }
}
```

On pourrait également les utiliser pour détecter que notre test ne s'arrete pas comme prévu : 

```java
@Test
public void stopDontEnd() {
    for (int i = 0; i < 5; i++) {
        if (cond) {
            return;
        }
    }
    fail("Should have returned before");
}
```

### 4.

`assertThrow` permet d'avoir un oracle plus propre dans le cas de test. En évitant de devoir écrire de `try-catch` pour attraper les Exceptons.
Cela permet de faciliter le test et d'améliorer la lisibilité de ces mêmes test.

Ce assert va également retourner l'exception, permettant d'effectuer des vérifications sur le message de cette exception par exemple.



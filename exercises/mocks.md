# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

# Answers : 

La raison pour laquelle même avec une méthode `prepareSocket` vide était que les stubs manuels définis pour SSLSocket ignoraient si setEnabledProtocols était réellement invoqué ou non.

Si la méthode `setEnableProtocols` des stubs n'était jamais appelé, aucune assertion ne s'exécutait et les tests passaient malgré l'absence de logique dans prepareSocket.

Pour contrer ce problème, on peut utiliser `verify` de Mockito pour s'assurer que la méthode setEnabledProtocols est bien appelée avec les bons arguments.

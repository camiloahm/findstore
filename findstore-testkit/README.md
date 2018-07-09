Find Store Test Kit
=========================

This project is used to create the performance test for Find Store Project. 

This project uses SBT 1.0, which is available [here](http://www.scala-sbt.org/download.html).


Start SBT
---------
Go to the root of the project and execute

```bash
$ sbt
```

Configure Environment 
-------------------
Please go to jumbo.Environment and change variables as needed


Run all simulations 
-------------------

Executes the performance tests excenarios

```bash
> gatling:test
```

Run a single simulation
-----------------------

```bash
> gatling:testOnly jumbo.StoreServiceSimulation
```

List all tasks
--------------------

```bash
> tasks gatling -v
```

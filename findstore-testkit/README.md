Find Store Test Kit
=========================

This project is used to create the performance tests for Find Store Project. Every scenario was created with [Gatling](https://gatling.io/) 

This project uses SBT 1.0, which is available [here](http://www.scala-sbt.org/download.html).


Last Performance Test Report
=========================

This test were running on a local machine with 4 cores and 16GB of ram memory

you can check the report [here](http://htmlpreview.github.com/?https://github.com/camiloahm/findstore/blob/master/findstore-testkit/reports/storeservicesimulation-1531176235517/index.html) . Notice that this report is display with Html Preview tool, some of the links may not work properly, to fix this please check the report on your local

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

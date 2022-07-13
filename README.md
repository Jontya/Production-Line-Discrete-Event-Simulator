

# Production Line Discrete Event Simulator
Simulate the production of “widgets” on a production line. The production line consists of a number of production 
stages, separated by inter-stage storage in the form of queues of finite length (size Qmax). The inter-stage storages 
are necessary because the time taken to process an widget at any production stage will vary due to random factors. The 
production line will be balanced in that the average time taken at any stage will be effectively equal.

### Running Program
``` javac PLSim.java ```
``` java PLSim 1000 1000 7 ```


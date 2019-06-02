cd server
java -cp .\compute.jar;.\ -Djava.rmi.server.codebase=http://localhost:80/RMI/ -Djava.rmi.server.hostname=localhost -Djava.security.policy=program.policy engine.ComputeEngine
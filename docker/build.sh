cd ..
mvn clean install
cd docker || exit
cp ../target/javacrud-1.0-SNAPSHOT.war .
docker build -f ./Dockerfile -t devou/javacrud:1 .
rm ./javacrud-1.0-SNAPSHOT.war
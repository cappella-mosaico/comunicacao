cd ..
JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 && mvn clean install
cd docker || exit
cp ../target/javacrud-1.0-SNAPSHOT.war .
docker build -f ./Dockerfile -t jcrud .
rm ./javacrud-1.0-SNAPSHOT.war
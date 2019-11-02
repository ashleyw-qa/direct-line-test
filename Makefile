up:
	docker-compose up -d

clean:
	docker-compose down

run-docker:
	mvn clean test -Dbrowser=remoteChrome

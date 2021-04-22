# build

gradlew build --warning-mode all

# test

gradlew test --warning-mode all

# run

gradlew :run --args="tweets server"

#url

Cargar usuario:
* PUT localhost:9090/tweet/@jesusmarana

Consulta:
* (todo)              GET localhost:9090/tweet
* (1 tweet)           GET localhost:9090/tweet/1309774842481213440
* (Filtro user)       GET localhost:9090/tweet?user=@jesusmarana
* (Filtro validados)  GET localhost:9090/tweet?user=@jesusmarana&validado=true
* (Validar)           POST localhost:9090/tweet/1309774842481213440
  {
  "validate": 1
  }


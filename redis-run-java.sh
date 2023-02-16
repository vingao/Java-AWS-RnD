java -Dspring.profiles.active=dev -Dserver.port=8090 -jar aws-demo.jar
<<comment
# Note: change 10.116.248.50 to the EC2 instance IP or localhost as needed
# set value: curl --noproxy '*' -X POST http://10.116.248.50:8090/redis/keys/a/500
# get value: curl --noproxy '*' http://10.116.248.50:8090/redis/keys/a
for i in {1..10}
do
    curl --noproxy '*' http://10.116.248.50:8090/redis/keys/a ; echo
done
comment
if [ -n "$1" ];then
  newSqlFile=src/main/resources/db/migration/V`date +%Y%m%d%H%I`__${1}.sql
  touch $newSqlFile
  echo "a new migration script generated at :"$newSqlFile
else
  echo "please input name"
fi
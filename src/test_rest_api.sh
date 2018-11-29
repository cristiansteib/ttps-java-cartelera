#!/bin/bash

URL="http://localhost:8080"


function printInfo {
    echo -e "\e[96m$1\e[0m\c"
}

########################

function test {
    endpoint=${URL}$1
    method=$2
    data=$3
    test_string=$4
    save_token=$5
    ref_token=$6

    OUT=$(curl -d "${data}" -X ${method} "${endpoint}" 2> /dev/null)


    if [[ $OUT == *"${test_string}"* ]]; then
      echo -e "\e[1m\e[92m..PASSED\e[0m"
    else
      echo -e "\e[1m\e[91m..FAIL\e[0m"
    fi
    echo -e "\e[33m»»» URL Tested: ${endpoint}\e[0m"
     echo $OUT
     echo ""
}


#todo: loguear al usuario y capturar el token!
TOKEN__ADMIN=''
TOKEN_ALUMNO=''

printInfo "»» Trying to login with invalid credentials."
test /auth/login POST "username=admin&password=admin" ":null"

printInfo "»» Trying to login with valid credentials for ADMIN user."
test /auth/login POST "username=admin&password=admin" ':"ok"'

printInfo "»» Trying to login with valid credentials for ALUMNO user."
test /auth/login POST "username=alumno&password=alumno" ':"ok"'

printInfo "»» Show all billboards for user ADMIN."
test /carteleras?token=${TOKEN_ADMIN} GET "" ':"ok"'

printInfo "»» Show all billboards for user ALUMNO."
test /carteleras?token=${TOKEN_ALUMNO} GET  "" ':"ok"'

printInfo "»» Don't show all billboards for a invalid token."
test /carteleras?token=435g GET  "" '403'
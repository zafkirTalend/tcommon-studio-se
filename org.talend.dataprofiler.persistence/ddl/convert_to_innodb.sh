#!/bin/sh


INPUT=$1
TMP_FILE="zzzz.zzzzz.zzzz"

sed -e s/"^);"/") ENGINE=InnoDB;"/g "${INPUT}" > "${TMP_FILE}"

mv "${INPUT}" "${INPUT}".bak
mv  "${TMP_FILE}" "${INPUT}"

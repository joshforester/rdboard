#!/bin/bash

Usage()
{
    echo "Usage: $0 [-h | -d <DAYS_SINCE_MODIFIED>]"
    echo ""
    echo "  -h: show usage"
    echo "  <DAYS_SINCE_MODIFIED>: count only files modified since this number of days ago"
    echo ""
}


# Argument verification
if [[ ${1} == '-h' ]]; then
    Usage
    exit
fi

if [[ ${1} == '-d' ]]; then
    FIND_ARGS="-mtime -${2}"
else
    FIND_ARGS=""
fi

FIND_CMD="find src"


# Run the total
TOTAL_COUNT=0
for FILE in `${FIND_CMD} ${FIND_ARGS} | grep -v 'src/tp/' | grep -v 'src/sql/bkup' | grep -v 'src/sql/patch' | grep -v '\~' | grep -v '\#' | grep -v ".tld$" | grep -v ".class$" | grep -v ".jar\|.jpg\|.ico\|.png\|.gif" | grep -v ".dtd$"`; do
    if [ -f $FILE ]; then
        FILE_COUNT=`wc -l $FILE | tr [:blank:] ":" | cut -f1 -d:`
        TOTAL_COUNT=$(( ${TOTAL_COUNT} + ${FILE_COUNT} ))
        echo ${FILE}:${FILE_COUNT}
    fi;
done
echo "TOTAL LINES:  ${TOTAL_COUNT}"


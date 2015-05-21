#!/bin/bash
#
# Josh Forester
# 2009/09/09
#
# This script upgrades the production database to be up to speed with development schema
# changes.  This should only be used during a site roll.
#

SCHEMA_UPGRADE_PATH=`pwd`

source ../leaderboardAdmCreds.profile

printUsage() {
        echo "Usage: $0"
        echo " [--dev|--prd] --version <schema upgrade version number>"
        return
}

# Vet arguments
if [ $# -ne 3 ] ; then
        printUsage
                exit 1
fi

case $2 in
--version) SCHEMA_UPGRADE_VERSION=$3;;
--help) printUsage; exit 1;;
"") ;;
*) printUsage; exit 1;;
esac

case $1 in
--dev) MYSQL_DB=leaderboard_dev;;
--prd) MYSQL_DB=leaderboard;;
--help) printUsage; exit 1;;
"") ;;
*) printUsage; exit 1;;
esac

mysql -u $MYSQL_USER -p -h $MYSQL_HOST $MYSQL_DB < ${SCHEMA_UPGRADE_PATH}/schemaUpgrade.${SCHEMA_UPGRADE_VERSION}.sql

exit 0


#!/bin/bash

set -e

DEFAULT_PWD_FILE=/etc/0_passwords.txt
PWD_FILE=/etc/1_passwords.txt


asadmin --user=admin --passwordfile=$DEFAULT_PWD_FILE change-admin-password --domain_name domain1

asadmin start-domain

asadmin --user=admin --passwordfile=$PWD_FILE enable-secure-admin

asadmin stop-domain

cp /usr/local/glassfish4/glassfish/domains/domain1/config/.domain.xml /usr/local/glassfish4/glassfish/domains/domain1/config/domain.xml

asadmin start-domain --verbose

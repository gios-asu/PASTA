#!/bin/sh

# This is the shell environment for the 'pasta' and 'tcat' user accounts
# and is used on both Turing and Babbage servers, respectively.
# In both cases it is sourced from ~/.bashrc in the shared account.
# The script sets a limited number of environment variables and aliases
# for managing, navigating, and supporting PASTA, NIS, Java, Ant, Perl,
# Tomcat, Metacat, and other relevant applications.
#
# Note that any settings that must differ locally from these settings
# would be declared in each system's '.bashrc' in the shared account on
# that server. This script defines settings that can be applied across 
# all servers.

# Tomcat variables and aliases

. /home/pasta/git/NIS/shell/env.sh

# Shutdown Tomcat using the default '$TOMCAT/bin/shutdown.sh'
/home/pasta/local/apache-tomcat/bin/shutdown.sh

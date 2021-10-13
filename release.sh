#!/usr/bin/env bash
set -euo pipefail

export JDK_JAVA_OPTIONS='--illegal-access=warn'
mvn release:clean release:prepare
mvn release:perform

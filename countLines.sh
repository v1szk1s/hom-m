#!/bin/bash

find . -name "*.java" -and -or -name "*.md" | xargs wc -l | tail -1
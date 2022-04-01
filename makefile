SRCS:=$(shell find . -name "*.java")

.PHONY: all
all:
	javac $(SRCS)
.PHONY: clean
clean:
	find . -name "*.class" | xargs rm

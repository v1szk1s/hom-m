SRCS:=$(shell find . -name "*.java")

.PHONY: all
all:
	@ mkdir -p build
	@ javac -d build $(SRCS)
.PHONY: clean
clean:
	find . -name "*.class" | xargs rm

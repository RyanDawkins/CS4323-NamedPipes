JFLAGS = -g
JC = javac
JCR = java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Client.java \
	Server.java \
	Pipe.java \
	PipeDoesNotExistException.java \
	PipeReader.java \
	PipeWriter.java


PIPES = \
	rdawkin-server \
	rdawkin-client

MAIN = Client

default: pipes classes run

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	@$(JCR) $(MAIN)

clean:
	$(RM) *.class *~

pipes:
	@$(RM) rdawkin-server
	@$(RM) rdawkin-client
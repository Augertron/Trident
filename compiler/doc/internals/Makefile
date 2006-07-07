#          This program was prepared by the Regents of the University of
#          California at Los Alamos National Laboratory (the University)
#          under contract No. W-7405-ENG-36 with the U.S. Department of
#          Energy (DOE). All rights in the program are reserved by the DOE
#          and the University.  Permission is granted to the public to
#          copy and use this software without charge, provided that this
#          Notice and any statement of authorship are reproduced on all
#          copies.  Neither the U.S. Government nor the University makes
#          any warranty, express or implied, or assumes any liability or
#          responsibility for the use of this software.

#############################################################################
# Make float compiler manual .pdf
#############################################################################

MANUAL = manual.pdf 

RM     = /bin/rm

TEX_FILES := ${shell echo *.tex }
STY_FILES := ${shell echo *.sty }
FIG_PDF_FILES := ${shell echo *.fig | grep -v '*' | sed -e 's@\.fig@.pdf@g'}
DOT_FIG_FILES := ${shell echo *.dot | grep -v '*' | sed -e 's@\.dot@.fig@g'}
EPS_FILES := ${shell echo *.fig | grep -v '*' | sed -e 's@\.fig@.eps@g'}

INTERMEDIATE_FILES = *.dvi *.ps *.pdf *.log *.bbl *.log *.aux *.blg 

.PRECIOUS: %.dvi %.pdf

manual : $(MANUAL)

manual.pdf : $(TEX_FILES) $(STY_FILES) 
	pdflatex manual.tex
	pdflatex manual.tex

$(DOT_FIG_FILES): %.fig : %.dot
	dot -Tfig -o $@ $<

clean :
	$(RM) -f *.dvi *.ps manual.pdf *.log *.bbl *.log *.aux *.blg 




:

# LA-CC 05-135 Trident @VERSION@
#
# Copyright Notice
# Copyright @YEAR@ (c) the Regents of the University of California.
#
# This Software was produced under a U.S. Government contract 
# (W-7405-ENG-36) by Los Alamos National Laboratory, which is operated by 
# the University of California for the U.S. Department of Energy. The U.S. 
# Government is licensed to use, reproduce, and distribute this Software. 
# Permission is granted to the public to copy and use this Software without 
# charge, provided that this Notice and any statement of authorship are 
# reproduced on all copies. Neither the Government nor the University makes 
# any warranty, express or implied, or assumes any liability or 
# responsibility for the user of this Software.

# echo $0 $1

  COMPS=`echo *.vhd | xargs -n1 | grep -v mux | grep -v Comps `
  LIB="mux2.vhd mux4.vhd $COMPS TridentComps.vhd"
  LIB_TARGET="trident"

  vlib $LIB_TARGET

  #echo $LIB

  for l in $LIB; do
    vcom -93 -work $LIB_TARGET $l > /dev/null 2>&1
    result=$?
    if [ $result -ne 0 ]; then
      echo "Error compiling $LIB_TARGET library."
      exit $result
    fi
  done

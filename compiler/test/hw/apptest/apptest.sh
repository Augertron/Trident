#! /bin/sh

# should check for tcc...
# top must be absolute
TOP=`pwd`

TEST_NAME="app_test"
DIR="app_test"
APP="examples/apps/euclid_d.c"

QUIX_LIB=/home/rasr/pkgs/quixilica/vhdl/

source $TOP/test/common/messages.sh


function mkbuild() {
  mkdir -p $TOP/build
  if [ $# -ne 0 ]; then
    mkdir -p $TOP/build/$1
  fi
}


#
# target
# build script 
# file source
#


function init_lib() {
  if [ ! -f  $TOP/build/$1/$1/$1.lib ]; then
    #set -x
    echo "building $1"
    mkbuild $1
    cp $2/build.sh $TOP/build/$1
    cp $3/*.vhd $TOP/build/$1
    ( cd $TOP/build/$1 ; sh ./build.sh )
    result=$?
    if [ $result -ne 0 ]; then
      echo "Could not build $1 -- exiting"
      exit -1
    fi
    #set +x
  fi
}

function init_ant() {
  run_echo "ant"
  ant compile > /dev/null 2>&1
  result=$?
  run_result "ant" $result
}

function init_paths() {
  APPDIR=$(dirname $APP)
  BASE=$(basename $APP)
  BASE_NOC=$(echo $BASE | cut -d . -f 1)

  CLASSPATH=$CLASSPATH:$TOP/build
  source /home/rasr/apps/llvm/llvm.sh
  LLV=$TOP/build/llv-obj/Debug/bin

  source /home/rasr/pkgs/riviera/setup.sh
  init_lib quixilica $TOP/lib/quixilica $QUIX_LIB 
  init_lib trident $TOP/lib/trident $TOP/lib/trident
}

function init_input() {
  run_echo "input file"
  test -f $TOP/$APP 
  result=$?
  run_result "input file -- where is it?" $result
  if [ -f $TOP/$APPDIR/"tb_"$BASE_NOC".vhd" ]; then
      TB_APP=$TOP/$APPDIR/"tb_"$BASE_NOC".vhd"
  fi
  if [ -f $TOP/$APPDIR/$BASE_NOC".fabric.in" ]; then
      TB_XD1=$TOP/$APPDIR/$BASE_NOC".fabric.in"
  fi

}
  

function init() {
  echo "$(basename $0) version 0.0 "
  echo "** Init $TEST_NAME"
  echo
  echo "This test takes the compiler through the complete process with"
  echo "the xd1, and it does try to test correctness of the output if "
  echo "a testbench is available."
  echo
  
  cd $TOP
  
  init_ant

  mkbuild $DIR

  init_input 

  init_paths


  # copy source
  mkbuild $DIR/$APPDIR/$BASE_NOC
  cp $TOP/$APP $TOP/build/$DIR/$APPDIR/$BASE_NOC

}

function rmbuild() {
  if [ $1 ]; then
    rm -rf $TOP/build/$1
  fi
}

function run_echo() {
    echo -n "Test $1"
}

function run_result() {
  if [ $2 -ne 0 ]; then
    echo_failure; echo
    echo "Failed $1."
    exit -1
  fi
  echo_success ; echo
}

function run_skipped() {
  echo_skipped ; echo
}

function run_tcc() {
   run_echo $1
   $TOP/tcc --llv=$LLV -t vhdl $BASE run > $BASE_NOC.out 2>&1
   result=$?
   run_result $1 $result
}

function run_vcom() {
   run_echo $1
   # if we were successful then we have a vhdl file.
   rm -rf work
   vlib work > $BASE_NOC.vhd.out 2>&1
   vmap work work >> $BASE_NOC.vhd.out 2>&1
   # this is weak
   vmap quixilica $TOP/build/quixilica/quixilica >> $BASE_NOC.vhd.out 2>&1
   vmap trident $TOP/build/trident/trident >> $BASE_NOC.vhd.out 2>&1
   vcom $BASE_NOC.vhd >> $BASE_NOC.vhd.out 2>&1
   result=$?
   run_result $1 $result
   vdel -all > /dev/null 2>&1
}  

function run_sim() {
  run_echo $1
  rm -rf work
  vlib work > $BASE_NOC.vhd.out 2>&1
  vmap work work >> $BASE_NOC.vhd.out 2>&1
  # this is weak
  vmap quixilica $TOP/build/quixilica/quixilica >> $BASE_NOC.vhd.out 2>&1
  vmap trident $TOP/build/trident/trident >> $BASE_NOC.vhd.out 2>&1
  vcom $BASE_NOC".vhd" >> $BASE_NOC.vhd.out 2>&1

  if [ -z $TB_APP ]; then
    run_skipped
    return
  fi

  cp $TB_APP .
  vcom "tb_"$BASE_NOC".vhd" >> $BASE_NOC.vhd.out 2>&1
  result=$?
  if [ $result -ne 0 ]; then
    run_result "Cannot compile tb_$1.vhd" $result
  fi
  egrep -e '-[-]+ @' "tb_"$BASE_NOC".vhd" | cut -d @ -f 2- | vsim > $BASE_NOC.sim.out 2>&1
  result=$?
  if [ $result -ne 0 ]; then
    run_result "Error during vsim" $result
  fi

  egrep "[Dd]one signal(s)? found" $BASE_NOC.sim.out > /dev/null 2>&1
  result=$?
  if [ $result -ne 0 ]; then
    run_result "Done not found during sim" $result
  fi

  grep "ERROR" $BASE_NOC.sim.out > /dev/null 2>&1
  result=$?
  if [ $result -eq 0 ]; then
    tail -40  $BASE_NOC.sim.out
    run_result "Errors found during sim" -1
  fi

  run_result $1 0
  vdel -all > /dev/null 2>&1

}   

function run_tcc_xd1() {
   run_echo $1
   $TOP/tcc --llv=$LLV --top -t vhdl $BASE run > $BASE_NOC.top.out 2>&1
   result=$?
   run_result $1 $result
}

function run_vcom_xd1() {
   run_echo $1
   #cp -r $TOP/test/hw/template .
   rsync -C -rl $TOP/test/hw/template .
   # other config? like the trident lib stuff...
   #
   cp $BASE_NOC.vhd template/hdl/user_app/tcc_design.vhd
   cd template
   LIBS="QUIXILICA=$TOP/build/quixilica/quixilica"
   LIBS="$LIBS TRIDENT=$TOP/build/trident/trident"
   make $LIBS compile > $BASE_NOC.xd1.out 2>&1
   result=$?
   run_result $1 $result
   cd ..
}

function run_sim_xd1() {
  run_echo $1
  if [ -z $TB_XD1 ]; then
    run_skipped
    return
  fi
}

function run_par_xd1() {
   run_echo $1
   if [ $PAR = $YES ]; then    
     cd template
     # Do not init Xilinx before it is needed.  Xilinx
     # does not play with Riviera (e.g. LD_LIBRARY_PATH
     source /home/rasr/pkgs/xilinx-ise-6.3p3/setup.sh

     make par50 > $BASE_NOC.par.out 2>&1
     result=$?
     run_result $1 $result
     cd par50
     make trace > $BASE_NOC.tim.out 2>&1
     cd ../..
   else
     run_skipped
     return
   fi
}


function run() {
   success=0
   failed=0

   cd $TOP/build/$DIR/$APPDIR/$BASE_NOC

   echo "** Running example $BASE "

   run_tcc "tcc"
   
   run_vcom "vcom"

   run_sim "sim"

   run_tcc_xd1 "tcc+xd1"

   run_vcom_xd1 "vcom+xd1"

   run_sim_xd1 "sim+xd1"

   run_par_xd1  "par+xd1"
      
   echo
   cd $TOP
}



function results() {
  echo ""
}


function clean() {
  if [ $SCHMUTZIG = $NO ]; then
    rmbuild $DIR/$APPDIR/$BASE_NOC
  fi
}


function fail() {
  echo -n "Break."
  echo
  exit -1
}


trap fail 2

NO="no"
YES="yes"
INIT=$NO
RUN=$NO
RESULTS=$NO
CLEAN=$NO
SCHMUTZIG=$NO
PAR=$NO

while [ $# != 0 ]
do  case "$1" in
  -t)   TOP="$2"; shift ;;
  -a)   APP="$2"; shift ;;
  -i)   INIT=$YES;;
  -u)   RUN=$YES;;
  -r)   RESULTS=$YES;;
  -p)   PAR=$YES;;
  -c)   CLEAN=$YES;;
  -d)   SCHMUTZIG=$YES;;
  -f)   INIT=$YES; RUN=$YES; RESULTS=$YES; ;;
  esac
  shift
done


if [ $INIT = $YES ]; then
  init_paths
  clean
  init
fi

if [ $RUN = $YES ]; then
  if [ -z $APPDIR ]; then
    init_paths
  fi
  run
fi

if [ $RESULTS = $YES ]; then
  results
fi

if [ $CLEAN = $YES ]; then
  clean
fi


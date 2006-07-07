:
# set -x
echo_success() {
  [ "$BOOTUP" = "color" ] && $MOVE_TO_COL
  echo -n "[  "
  [ "$BOOTUP" = "color" ] && $SETCOLOR_SUCCESS
  echo -n "OK"
  [ "$BOOTUP" = "color" ] && $SETCOLOR_NORMAL
  echo -n "  ]"
  echo -ne "\r"
  return 0
}

echo_failure() {
  [ "$BOOTUP" = "color" ] && $MOVE_TO_COL
  echo -n "["
  [ "$BOOTUP" = "color" ] && $SETCOLOR_FAILURE
  echo -n "FAILED"
  [ "$BOOTUP" = "color" ] && $SETCOLOR_NORMAL
  echo -n "]"
  echo -ne "\r"
  return 1
}

echo_passed() {
  [ "$BOOTUP" = "color" ] && $MOVE_TO_COL
  echo -n "["
  [ "$BOOTUP" = "color" ] && $SETCOLOR_WARNING
  echo -n "PASSED"
  [ "$BOOTUP" = "color" ] && $SETCOLOR_NORMAL
  echo -n "]"
  echo -ne "\r"
  return 1
}

echo_skipped() {
  [ "$BOOTUP" = "color" ] && $MOVE_TO_COL
  echo -n "["
  [ "$BOOTUP" = "color" ] && $SETCOLOR_SKIPPED
  echo -n "IGNORE"
  [ "$BOOTUP" = "color" ] && $SETCOLOR_NORMAL
  echo -n "]"
  echo -ne "\r"
  return 1
}

BOOTUP=color
RES_COL=60
MOVE_TO_COL="echo -en \\033[${RES_COL}G"
SETCOLOR_SUCCESS="echo -en \\033[1;32m"
SETCOLOR_FAILURE="echo -en \\033[1;31m"
SETCOLOR_WARNING="echo -en \\033[1;33m"
SETCOLOR_NORMAL="echo -en \\033[0;39m"
SETCOLOR_SKIPPED="echo -en \\033[1;34m"




NO="no"
YES="yes"
INIT=$NO
RUN=$NO
RESULTS=$NO
CLEAN=$NO



while [ $# != 0 ]
do  case "$1" in
  -t)   TOP="$2"; shift ;;
  -i)   INIT=$YES;;
  -u)   RUN=$YES;;
  -r)   RESULTS=$YES;;
  -c)   CLEAN=$YES;;
  -f)   INIT=$YES; RUN=$YES; RESULTS=$YES; CLEAN=$YES;;
  esac
  shift
done



function mkbuild() {
  mkdir -p $TOP/build
  if [ $# -ne 0 ]; then
    mkdir -p $TOP/build/$1
  fi
}

function copyExamples() {
  cp -r $TOP/examples $TOP/build/$1
}


function init_results() {
  mkbuild "results"
  cp /dev/null $TOP/build/results/$TEST_NAME.xml
  cp /dev/null $TOP/build/results/"$TEST_NAME"_long.xml
  append_result "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
  append_result_short "<?xml-stylesheet type=\"text/xsl\" href=\"test.xsl\"?>"
  append_result_long "<?xml-stylesheet type=\"text/xsl\" href=\"long.xsl\"?>"
  append_result "<result name=\"$TEST_NAME\" date=\"$(date)\">"
}

function append_result() {
  echo $1 >> $TOP/build/results/$TEST_NAME.xml
  echo $1 >> $TOP/build/results/"$TEST_NAME"_long.xml
}

function append_result_short() {
  echo $1 >> $TOP/build/results/$TEST_NAME.xml
}

function append_result_long() {
  echo $1 >> $TOP/build/results/"$TEST_NAME"_long.xml
}



function end_results() {
  append_result "</result>" 
}

function init() {
  echo "Init $TEST_NAME"
  mkbuild $DIR
  copyExamples $DIR

  CLASSPATH=$CLASSPATH:$TOP/build
  source /home/rasr/apps/llvm/llvm.sh
  LLV=$TOP/build/llv-obj/Debug/bin

  source /home/rasr/pkgs/riviera/setup.sh
  QUIX_LIB=/data0/stuff/junk/quixilica/quixilica

  init_results

}


function rmbuild() {
  if [ $1 ]; then
    rm -rf $TOP/build/$1
  fi
}

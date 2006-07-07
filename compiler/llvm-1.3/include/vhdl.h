/*
 *
 @LICENSE@
 */


#ifndef TARGET_VHDL_H
#define TARGET_VHDL_H

namespace llvm {
  /// Command line options shared between TargetMachine implementations - 
  /// these should go in their own header eventually.
  ///

  /*
    decleared elsewhere?

  extern bool PrintMachineCode;
  */
  class TargetMachine;
  class Module;
  class IntrinsicLowering;
  

  // allocateCTargetMachine - Allocate and return a subclass of TargetMachine
  // that implements emits C code.  This takes ownership of the
  // IntrinsicLowering pointer, deleting it when the target machine is
  // destroyed.
  //
  /*
  TargetMachine *allocateCTargetMachine(const Module &M,
                                        IntrinsicLowering *IL = 0);
  */

  // this feels like a hack?
  
  TargetMachine *allocateVTargetMachine(const Module &M,
                                        IntrinsicLowering *IL = 0);
} // End llvm namespace

#endif






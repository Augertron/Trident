/*
 *
 @LICENSE@
 */


//===-- CTargetMachine.h - TargetMachine for the C backend ------*- C++ -*-===//
// 
//                     The LLVM Compiler Infrastructure
//
// This file was developed by the LLVM research group and is distributed under
// the University of Illinois Open Source License. See LICENSE.TXT for details.
// 
//===----------------------------------------------------------------------===//
// 
// This file declares the TargetMachine that is used by the C backend.
//
//===----------------------------------------------------------------------===//

#ifndef VTARGETMACHINE_H
#define VTARGETMACHINE_H

#include "llvm/Target/TargetMachine.h"

namespace llvm {
class IntrinsicLowering;

struct VTargetMachine : public TargetMachine {
  VTargetMachine(const Module &M, IntrinsicLowering *IL) :
    TargetMachine("VBackend", IL, M) {}

  // This is the only thing that actually does anything here.
  virtual bool addPassesToEmitAssembly(PassManager &PM, std::ostream &Out);


  // This class always works, but shouldn't be the default in most cases.
  static unsigned getModuleMatchQuality(const Module &M) { return 1; }
};

} // End llvm namespace


#endif

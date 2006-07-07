/*
 *
 @LICENSE@
 */



// Trident (C) 2005 The Regents of the University of California.
// Written by Neil Steiner

#ifndef RENAME_DUPLICATE_VARS_H
#define RENAME_DUPLICATE_VARS_H

#include "llvm/Pass.h"

namespace llvm {

  // declare the two functions passes that collaborate to replace duplicate variable 
  // names with unique names; note that both of these passes must be added to the 
  // PassManager
  FunctionPass* createCollectVariableNamesPass();
  FunctionPass* createRenameDuplicateVarsPass();

}

#endif

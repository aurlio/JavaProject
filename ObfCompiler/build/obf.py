#!/usr/bin/env python
# -*- coding: utf-8 -*-

'''
this file is an example, modify this for your module.
@file: obf.py
@author: jupeng.xia@alipay.com

'''

import os,sys
run = os.system
cwd = os.getcwd()
root_dir = os.path.join(cwd,"..","..")
build_dir = os.path.join(root_dir, "ObfCompiler", "build")
runner_dir = os.path.join(root_dir, "runner")

############################################################
## config here
module = "merchant"
module_dir = os.path.join(root_dir, module)
algo_file = os.path.join(runner_dir,"algo.js")
module_file = os.path.join(module_dir, module+".js")
encode_function_file = os.path.join(runner_dir, "encode.js")
obf_input_file = os.path.join(module_dir, "merge-"+module+".js")
obf_output_file = os.path.join(module_dir, "mix-"+module+".js")
compress_output = os.path.join(module_dir, "zip-"+module+".js")
obf_round = 5 #notice 10 is very slow.
############################################################

obf_cmd = r'java -cp ;%s\compiler.jar;%s\obf.jar app.ObfCompiler %s %s' % (build_dir, build_dir, obf_input_file, obf_output_file)
round_obf_cmd = r'java -cp ;%s\compiler.jar;%s\obf.jar app.ObfCompiler %s %s' % (build_dir, build_dir, obf_output_file, obf_output_file)
compress_cmd = r'java -cp ;%s\compiler.jar;%s\compress.jar app.CompressCompiler %s %s' % (build_dir, build_dir, obf_output_file, compress_output)

print("[*] obf round=%d" % obf_round)

#merge file
#read encode file content
f = open(encode_function_file,"rb")
encode_content = f.read()
f.close()

f = open(algo_file,"rb")
algo_content = f.read()
f.close()

f = open(module_file,"rb")
module_content = f.read()
f.close()

input = algo_content+module_content
f = open(obf_input_file,"wb")
f.truncate()
f.write(input)
f.close()

cmds = []
cmds.append(obf_cmd)
for i in range(obf_round-1):
    cmds.append(round_obf_cmd)
    
round_count = 0;
for cmd in cmds:
    round_count+=1
    print("[*] in round %d" % round_count)
    assert(0==run(cmd))
print("[+] obf %d round, complete." % round_count)

f = open(obf_output_file,"rb")
obf_output_content = f.read()
f.close()
content = encode_content+obf_output_content

f = open(obf_output_file,"wb")
f.truncate()
f.write(content)
f.close()

#compress
assert(0==run(compress_cmd))

print("[+] compress over, all complete.")

os.chdir(module_dir)
run("python.exe test.py")
os.chdir(cwd)
print("[*] test over.")
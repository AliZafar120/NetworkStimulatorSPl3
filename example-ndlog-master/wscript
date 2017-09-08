## -*- Mode: python; py-indent-offset: 4; indent-tabs-mode: nil; coding: utf-8; -*-

def build(bld):
    module = bld.create_ns3_module('fourhops', ['internet-stack'])
    module.source = [
        'fourhops.cc',
        ]
    headers = bld.new_task_gen('ns3header')
    headers.module = 'fourhops'
    headers.source = [
        'fourhops.h',
        'fourhops-helper.h',
        ]

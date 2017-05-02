import os

#project paths
SRC_DIR = os.path.dirname(os.path.abspath(__file__))
PRJ_DIR = '/'.join(SRC_DIR.split('/')[:-1])
LIB_DIR = '{}/{}'.format(PRJ_DIR, 'lib')
DATA_DIR = '{}/{}'.format(LIB_DIR, 'data')

#project constants

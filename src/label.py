annotation_path = 'annotation/'
labels = [
    {'name':'charge', 'id':1},
    {'name':'blast', 'id':2},
]
with open(annotation_path + 'label_map.pbtxt', 'w') as f:
    for label in labels:
        f.write('items { \n')
        f.write('\tname:\'{}\'\n'.format(label['name']))
        f.write('\tid:{}\n'.format(label['id']))
        f.write('}\n')
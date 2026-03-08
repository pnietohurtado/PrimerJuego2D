import os

collision_file = "res/maps/collision.txt"
with open(collision_file, "r") as f:
    lines = f.read().splitlines()

filenames = []
for i in range(0, len(lines), 2):
    filenames.append(lines[i])

html = """<!DOCTYPE html>
<html>
<head>
<style>
  body { font-family: monospace; display: flex; flex-wrap: wrap; gap: 10px; }
  .tile { border: 1px solid #ccc; text-align: center; width: 80px; padding: 5px; }
  .tile img { width: 48px; height: 48px; image-rendering: pixelated; }
</style>
</head>
<body>
"""

for i, fn in enumerate(filenames):
    html += f'<div class="tile"><div>{i}</div><img src="res/Tiles2/{fn}"></div>\n'

html += """</body>
</html>"""

with open("tiles_preview.html", "w", encoding="utf-8") as f:
    f.write(html)

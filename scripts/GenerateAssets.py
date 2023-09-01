from tkinter import Image
from math import floor as fl

from PIL import Image

import os

def grayscalify(path: str):
    """Make an image grayscale.

    Args:
        path (str): Path of the file to grayscale.
    """
    Image.open(path).convert('LA').save(path)
    
def tint(path: str, RGB: tuple):
    """Tint an image a color with RGB values. Floors RGB values to integers.

    Args:
        path (str): Path of the file to tint.
        RGB (tuple): RGB values to tint the image with.
    """
    image = Image.open(path).convert('RGBA')
    output = []
    for pix in image.getdata():
        # If the alpha value is 0, then the pixel is transparent, so we don't want to change it.
        if pix[3] in list(range(1, 256)):
            output.append((pix[0] + fl(RGB[0]), pix[1] +
                          fl(RGB[1]), pix[2] + fl(RGB[2])))
        else:
            output.append(pix)
    image.putdata(output)
    image.save(path)

def invert(path: str):
    """Invert the colors of an image.

    Args:
        path (str): The path of the image to invert.
    """
    image = Image.open(path).convert('RGBA')
    output = []
    for pix in image.getdata():
        if pix[3] in list(range(1, 256)):
            output.append((0, 0, 0, 0))
        else:
            output.append((0, 0, 0, 255))
    image.putdata(output)
    image.save(path)

for mat in os.listdir("scripts/assets/materials/"):
    if mat.endswith(".png"):
        path = "scripts/assets/materials/" + mat
        for stage in os.listdir("scripts/assets/crops/wheat/"):
            stage_path = "scripts/assets/crops/wheat/" + stage
            material = Image.open(path)
            material.save(path)
            stage_img = Image.open(stage_path).convert('RGBA')
            stage_path = "scripts/assets/temp/wheat/" + stage
            stage_img.save(stage_path)
            stage_img = Image.open(stage_path).convert('RGBA')
            mask = Image.open(stage_path).convert('LA')
            invert(stage_path)
            Image.composite(material.convert('RGB'), stage_img.convert("RGB"), mask).save(f"src/main/resources/assets/lazycrops/textures/block/" + mat.split(".")[0] + "_crop_" + stage.split("_")[1].split(".")[0] + ".png")

# Basically, we just remove the pure white pixels from the textures and replace them with transparent pixels.
for texture in os.listdir("src/main/resources/assets/lazycrops/textures/block/"):
    if texture.endswith(".png"):
        replaced = []
        for pix in Image.open("src/main/resources/assets/lazycrops/textures/block/" + texture).convert("RGBA").getdata():
            if pix[0] == 0 and pix[1] == 0 and pix[2] == 0 and pix[3] == 255:
                replaced.append((0, 0, 0, 0))
            else:
                replaced.append(pix)
        finish = Image.open("src/main/resources/assets/lazycrops/textures/block/" + texture).convert("RGBA")
        finish.putdata(replaced)
        finish.save("src/main/resources/assets/lazycrops/textures/block/" + texture)

for mat in os.listdir("scripts/assets/materials/"):
    if mat.endswith(".png"):
        material_texture = Image.open("scripts/assets/materials/" + mat).convert("RGBA")
        seed_texture = Image.open("scripts/assets/seed.png").convert("RGBA")
        material_texture.save("scripts/assets/temp/materials/" + mat)
        seed_texture.save("scripts/assets/temp/seeds/" + mat)
        material_texture = Image.open("scripts/assets/temp/materials/" + mat).convert("RGBA")
        seed_texture = Image.open("scripts/assets/temp/seeds/" + mat).convert("RGBA")
        mask = Image.open("scripts/assets/temp/seeds/" + mat).convert("LA")
        invert("scripts/assets/temp/seeds/" + mat)
        Image.composite(material_texture.convert('RGB'), seed_texture.convert("RGB"), mask).save(f"src/main/resources/assets/lazycrops/textures/item/" + mat.split(".")[0] + "_seeds.png")
        replaced = []
        for pix in Image.open("src/main/resources/assets/lazycrops/textures/item/" + mat.split(".")[0] + "_seeds.png").convert("RGBA").getdata():
            if pix[0] == 0 and pix[1] == 0 and pix[2] == 0 and pix[3] == 255:
                replaced.append((0, 0, 0, 0))
            else:
                replaced.append(pix)
        finish = Image.open("src/main/resources/assets/lazycrops/textures/item/" + mat.split(".")[0] + "_seeds.png").convert("RGBA")
        finish.putdata(replaced)
        finish.save("src/main/resources/assets/lazycrops/textures/item/" + mat.split(".")[0] + "_seeds.png")
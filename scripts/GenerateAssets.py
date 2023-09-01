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

def average_color(path: str):
    """Average the color of an image.

    Args:
        image (str): The path of the image to average the color of.
    """
    r = 0
    g = 0
    b = 0
    image = Image.open(path).convert('RGBA')
    for pixel in image.getdata():
        r += pixel[0]
        g += pixel[1]
        b += pixel[2]
    pixel_count = len(image.getdata())
    return ((r/pixel_count), (g/pixel_count), (b/pixel_count))

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
            material_path = "scripts/assets/crops/wheat/" + stage
            crop = Image.open(path)
            crop.save(path)
            material = Image.open(material_path).convert('RGBA')
            material_path = "scripts/assets/temp/wheat/" + stage
            material.save(material_path)
            material = Image.open(material_path).convert('RGBA')
            mask = Image.open(material_path).convert('LA')
            invert(material_path)
            Image.composite(crop.convert('RGB'), material.convert("RGB"), mask).save(f"src/main/resources/assets/lazycrops/textures/block/" + mat.split(".")[0] + "_crop_" + stage.split("_")[1].split(".")[0] + ".png")

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
        


# print(average_color("scripts/assets/dirt.png"))
# dirt_crop = Image.open("scripts/assets/wheat_stage7.png").crop((0, 0, 16, 16))
# dirt_crop.save("scripts/assets/dirt_crop.png")
# grayscalify("scripts/assets/dirt_crop.png")
# wheat_mask = Image.open("scripts/assets/wheat_stage7.png").convert('LA')
# wheat_mask.save("scripts/assets/wheat_mask.png")
# invert("scripts/assets/wheat_mask.png")
# wheat_mask = Image.open("scripts/assets/wheat_mask.png")
# Image.composite(Image.open("scripts/assets/dirt_crop.png").convert('RGBA'), Image.open("scripts/assets/dirt.png").convert('RGBA'), wheat_mask.convert('LA')).save("scripts/assets/dirt_crop.png")

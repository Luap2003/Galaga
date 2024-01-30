from PIL import Image

def get_non_black_pixels(image_path,crop_pixels=30):
    # Load the image
    img = Image.open(image_path)
    width, height = img.size
    left = crop_pixels
    top = crop_pixels
    right = width - crop_pixels
    bottom = height - crop_pixels

    # Crop the image
    img = img.crop((left, top, right, bottom))
    #img.resize((10, 10), Image.NEAREST).show()  # NEAREST filter for pixel art effect
    non_black_pixels = []
    width, height = img.size
    for x in range(width):
        for y in range(height):
            if (x) % ( (width) // 20 ) == 0 and (y) % ( (height) // 20 ) == 0:
                pixel = img.getpixel((x, y))
                if pixel != (0, 0, 0) and pixel != (0, 0, 0, 0):  # Check for both RGB and RGBA formats
                    # Append the pixel data in a format suitable for Java (x, y, R, G, B)
                    non_black_pixels.append((((x)//((width)//20))*5, ((y)//((height)//20))*5, pixel[0], pixel[1], pixel[2]))
    return non_black_pixels

# Replace 'path_to_your_image.png' with your image file path
pixels = get_non_black_pixels('/home/paul/Documents/paul/uni/se/projekt/Imgres.png')

# Print the result
for p in pixels:
    print(f"g.setColor(new Color({p[2]}, {p[3]}, {p[4]}));")
    print(f"g.fillRect(x+{p[0]}, y+{p[1]}, 5, 5);")